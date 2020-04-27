package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.user.customer.Customer;

import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Repository
public class TruckDAO {
    @Autowired
    private TruckRepository truckRepo;
    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    public void setTruckRepo(TruckRepository truckRepo) {
        this.truckRepo = truckRepo;
    }

    @Autowired
    private UserDAO userDAO;


    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }



    public void save(Truck t){
        this.truckRepo.save(t);
    }

    public void update(Truck t) {
        this.truckRepo.save(t);
    }

    /**************************************************************
     * Image Added Stuff
     **************************************************************/
    public Image saveMenu(Integer truckID, MultipartFile file) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            if(truck.get().getMenu() != null) {
                this.deleteMenu(truckID);
            }
            Image i = this.imageDAO.saveFile(file);

            truck.get().setMenu(i);

            this.truckRepo.save(truck.get());
            return i;
        }
        throw new RuntimeException("Failed to save menu");
    }

    public Image getMenu(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            return truck.get().getMenu();
        }
        throw new RuntimeException("Failed to get menu");
    }

    public void deleteMenu(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            Image i = truck.get().getMenu();
            Image testImage = this.imageDAO.getFile(i.getId());
            if(i != null) {
                this.imageDAO.deleteFile(i.getId());
            }
            truck.get().setMenu(null);
            this.truckRepo.save(truck.get());
        }
    }

    public Image savePicture(Integer truckID, MultipartFile file) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {

            Image i = this.imageDAO.saveFile(file);

            List<Image> pictures = truck.get().getPictures();

            pictures.add(i);
            truck.get().setPictures(pictures);

            this.truckRepo.save(truck.get());
            return i;
        }
        throw new RuntimeException("Failed to save Picture");
    }

    public List<Image> saveAllPicture(Integer truckID, MultipartFile[] file) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);

        List<Image> newImages = new ArrayList<>();
        if(truck.isPresent()) {
            if(!truck.get().getPictures().isEmpty()) {
                this.deleteAllPicture(truckID);
            }

            for(MultipartFile f: file) {
                Image i = this.imageDAO.saveFile(f);
                newImages.add(i);
            }


            truck.get().setPictures(newImages);
            this.truckRepo.save(truck.get());
            return newImages;
        }
        throw new RuntimeException("Failed to save Picture");
    }

   /* public List<Image> getPictures(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            return truck.get().getPictures();
        }
        throw new RuntimeException("Failed to get Pictures");
    }*/

    public List<String> getPictureIds(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
           List<String> str = new ArrayList<>();
           for(Image i: truck.get().getPictures()) {
               str.add(i.getId());
           }
            return str;
        }
        throw new RuntimeException("Failed to get Picture ids");
    }

    public void deletePicture(String imageID, Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        Image oldImage = this.imageDAO.getFile(imageID);
        if(truck.isPresent()) {
            List<Image> i = truck.get().getPictures();
            i.remove(oldImage);

            truck.get().setPictures(i);
            this.truckRepo.save(truck.get());
            this.imageDAO.deleteFile(imageID);
        }
    }

    public void deleteAllPicture(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            List<Image> i = truck.get().getPictures();
            for(Image old: i) {
                this.imageDAO.deleteFile(old.getId());
            }

            truck.get().setPictures(new ArrayList<>());
            this.truckRepo.save(truck.get());
        }
    }



    /**************************************************************
     * End Image Added Stuff
     **************************************************************/

    public void deleteById(Integer truckID) {
        this.truckRepo.deleteById(truckID);
    }

    public Iterable<Truck> getAllTrucks() {
        Iterable<Truck> trucks = truckRepo.findAll();
        trucks.forEach(t -> {t.setMenu(null); t.setPictures(null);});
        return trucks;
    }

    public List<Truck> findByName(String name){
        List<Truck> trucks = this.truckRepo.findByNameIgnoreCaseContaining(name);
        trucks.forEach(t -> {t.setMenu(null); t.setPictures(null);});
        return trucks;
    }

    public List<Truck> searchAdvanced(TruckDTO truck) {
        int NUM_RECS = 50;

        if(truck == null) {
            throw new RuntimeException("Invalid object given");
        }

        Iterable<Truck> suggestions = truckRepo.findAll();
        suggestions.forEach(t -> {t.setMenu(null); t.setPictures(null);});

        //Create a map to sort trucks based on scores
        Map<Integer, List<Truck>> truckScores = new HashMap<Integer, List<Truck>>();

        for(Truck t : suggestions){
            int score = getScore(t, truck);

            //If the truck does not contain the score, add it
            if(!truckScores.containsKey(score)){
                truckScores.put(score, new ArrayList<Truck>());
            }
            //Add the truck to the right score bracket
            truckScores.get(score).add(t);
        }
        List<Truck> result = new ArrayList<>();

        int highscore = 4;
        while(highscore >= 0 && result.size() < NUM_RECS){
            if(truckScores.get(highscore) != null){
                for(Truck t : truckScores.get(highscore)){
                    if(result.size() < NUM_RECS){
                        result.add(t);
                    }
                }
            }
            highscore--;
        }
        return result;
    }

    /*=============================================================
              START: Ashley's attempt at an advanced search
    =============================================================*/
    public List<Truck> superAdvancedSearch(SearchDTO searchParam) {

        if(searchParam == null) {
            throw new RuntimeException("Invalid search parameters given");
        }

        // gather all trucks from database
        Iterable<Truck> suggestions = truckRepo.findAll();
        suggestions.forEach(t -> t.setMenu(null));

        Map<Truck, Integer> searchResults = new HashMap<>();

        // cycle through each search parameter, scoring each truck (0-6)
        // ignore null/empty/zero parameters
        for (Truck t : suggestions) {

            if (t == null) {
                continue;
            }

            int score = 0;

            // check name
            if (!"".equals(searchParam.getName())) {

                if (searchParam.getName() != null && t.getName() != null && t.getName().contains(searchParam.getName())) {
                    score++;
                }
                if(searchParam.getName() != null && t.getName() != null && t.getName().equals(searchParam.getName())){
                    score += 2;
                }
            }
            else {
                score++;
            }

            // check rating
            if (searchParam.getRating() != null && searchParam.getRating() != 0) {
                if (t.getAvgRating() != null && searchParam.getRating() <= t.getAvgRating()) {
                    score++;
                }
            }
            else {
                score++;
            }

            // check price
            if (searchParam.getPrice() != null && searchParam.getPrice() != 0) {
                if (t.getPrice() != null && searchParam.getPrice() >= t.getPrice().ordinal()) {
                    score++;
                }
            }
            else {
                score++;
            }

            // check food type
            if (searchParam.getFoodType() != null) {
                if (checkFood(t, searchParam.getFoodType())) {
                    score++;
                }
            }
            else {
                score++;
            }

            // check if open
            if (searchParam.isOpen()) {
                if (checkIfOpen(t)) {
                    score += 2;
                }
                else {
                    score--;
                }
            }
            else {
                score++;
            }

            // check location
            if (searchParam.getLocation() != null) {
                if (checkIfNearby(t, searchParam.getLocation())) {
                    score += 2;
                }

                // set distance from user
                t.setDistanceFromUser(getDistanceFromUser(t, searchParam.getLocation()));
            }
            else {

                // set default distance for when there's no specified location
                t.setDistanceFromUser(-1.0);
                score++;
            }

            // check if score is high enough to be sent as a result
            if (score >= 4) {
                searchResults.put(t, score);
            }
        }

        // sort results by score (in descending order)
        List<Map.Entry<Truck, Integer>> sorter = new ArrayList<>(searchResults.entrySet());
        sorter.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(sorter);

        List<Truck> highest = new ArrayList<>();
        sorter.forEach(e -> highest.add(e.getKey()));


        return highest;
    }

    // checks if truck's food types contains the given type
    private boolean checkFood(Truck t, String[] s) {
        if(t.getFood() == null){
            return false;
        }
        for (Food f : t.getFood()) {
            if (f.getFoodtype() != null && Arrays.asList(s).contains(f.getFoodtype())) {
                return true;
            }
        }
        return false;
    }

    // checks if truck is currently open
    private boolean checkIfOpen(Truck t) {
        if(t.getRoute() == null){
            return false;
        }
        for (Route r : t.getRoute()) {

            if (r.getStartTime() == null || r.getEndTime() == null) {
                continue;
            }

            if (r.getStartTime().before(new Date()) && r.getEndTime().after(new Date())) {
                return true;
            }
        }
        return false;
    }

    // checks if truck is nearby
    private boolean checkIfNearby(Truck t, Location l) {
        if(t.getRoute() == null){
            return false;
        }
        for (Route r : t.getRoute()) {

            if (r.getLocation() == null || r.getLocation().getLatitude() == null
                    || r.getLocation().getLongitude() == null) {
                continue;
            }

            if (checkDistance(l, r.getLocation(), 20)) {
                return true;
            }
        }
        return false;
    }

    // gets truck's distance from user using its average location
    private double getDistanceFromUser(Truck t, Location l) {

        Location tLocation = getAvgLocation(t);

        return Location.getDistance(l, tLocation);
    }

    // gets truck's average location
    private Location getAvgLocation(Truck t) {
        double tempLat = 0.0;
        double tempLong = 0.0;

        for (Route r : t.getRoute()) {
            tempLat += r.getLocation().getLatitude();
            tempLong += r.getLocation().getLongitude();
        }

        if (t.getRoute().size() > 0) {
            tempLat /= t.getRoute().size();
            tempLong /= t.getRoute().size();
        }

        return new Location(tempLong, tempLat);
    }

    /*=============================================================
              END: Ashley's attempt at an advanced search
    =============================================================*/

    public Integer getScore(Truck t, TruckDTO truck){
        int score = 0;
        if(truck.getId() != null && truck.getId().equals(t.getId())) {
            score = score + 1;
        }
        if(truck.getPrice() != null && truck.getPrice() == t.getPrice()) {
            score = score + 1;
        }
        if(truck.getName() != null && t.getName().contains(truck.getName())) {
            score = score + 1;
        }
        if(truck.getFood() != null) {
            boolean oneMatch = false;
            for (Food f : truck.getFood()) {
                if(oneMatch) {
                    break;
                }
                for (Food f2 : t.getFood()) {
                    if(f == f2) {
                        score = score + 1;
                        oneMatch = true;
                        break;
                    }
                }
            }
        }
        /*if(truck.getFood() != null && truck.getFood() == t.getFood()) {
            score = score + 1;
        }*/
        return score;
    }




    public List<Truck> findByOwner(String username) {
        Integer id = this.userDAO.findByUsername(username).getId();
        List<Truck> trucks = this.truckRepo.findByOwner_id(id);
        trucks.forEach(t -> {t.setMenu(null); t.setPictures(null);});
        return trucks;
    }
    public List<Truck> findByOwner(Integer id) {
        List<Truck> trucks = this.truckRepo.findByOwner_id(id);
        trucks.forEach(t -> {t.setMenu(null); t.setPictures(null);});
        return trucks;
    }


    public List<Object> getSubscribers(Integer id) {
        List<Object> returns = new ArrayList<>();


        Optional<Truck> truck = truckRepo.findById(id);

        if(truck.isPresent()) {
            Optional<User> user = userDAO.findById(truck.get().getOwner().getId());
            if(user.isPresent()) {
                List<Object> userInfo = new ArrayList<>();
                userInfo.add(user.get().getId());
                userInfo.add(user.get().getUsername());
                returns.add(userInfo);
            }
            for(Customer c: truck.get().getCustomers()) {
                Optional<User> customerUser = userDAO.findById(c.getId());
                if(customerUser.isPresent()){
                    returns.add(customerUser.get().getUsername());
                }
            }
            //returns.addAll(truck.get().getCustomers());
            return returns;
        }
       return null;
    }

    public Optional<Truck> findById(Integer id) {
        Optional<Truck> trucks = this.truckRepo.findById(id);
        trucks.get().setMenu(null);
        return trucks;
    }

    public List<Truck> findALL() {
        Iterable<Truck> iter =  this.truckRepo.findAll();
        iter.forEach(t -> {t.setMenu(null); t.setPictures(null);});

        return StreamSupport.stream(iter.spliterator(), false)
                        .collect(Collectors.toList());
    }



    public List<Truck> getNearbyTrucks(Location loc, Integer distance) {

        //If no distance is given we will default to a 20 mile radius
        if(distance == null) {
            distance = 20;
        }
        List<Truck> allTrucks = (List<Truck>) this.truckRepo.findAll();
        allTrucks.forEach(t -> {t.setMenu(null); t.setPictures(null);});

        List<Truck> inRange = new ArrayList<>();
        for(Truck t: allTrucks) {
            List<Route> truckRoutes = t.getRoute();
            for(Route r: truckRoutes) {
                if(r.getLocation() != null && checkDistance(loc, r.getLocation(), distance)) {
                    inRange.add(t);
                    break;
                }
            }
        }

        return inRange;
    }


    public Boolean checkDistance(Location locGiven, Location locTest, Integer distance) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        if(locGiven.getLatitude() == null || locGiven.getLongitude() == null || locTest.getLatitude() == null || locTest.getLongitude() == null) {
            return false;
        }




        double R = 3958.8;

        double lat1 = Math.toRadians(locGiven.getLatitude());
        double lat2 = Math.toRadians(locTest.getLatitude());
        double difLat = Math.toRadians(locGiven.getLatitude() - locTest.getLatitude());
        double difLon = Math.toRadians(locGiven.getLongitude() - locTest.getLongitude());


        double a = Math.pow(Math.sin(difLat/2), 2.0) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(difLon/2), 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));



        if(( c * R ) <= (double)distance) {
            return true;
        }
        return false;
    }

    public Integer getNumSubscribers(Integer truckID) {
        Optional<Truck> truck = truckRepo.findById(truckID);
        if(truck.isPresent()) {
            return truck.get().getCustomers().size();
        }
        return null;
    }
}
