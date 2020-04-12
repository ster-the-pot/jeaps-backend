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
            if(i != null) {
                this.imageDAO.deleteFile(i.getId());
            }
            truck.get().setMenu(null);
            this.truckRepo.save(truck.get());
        }
    }



    public void deleteById(Integer truckID) {
        this.truckRepo.deleteById(truckID);
    }

    public Iterable<Truck> getAllTrucks() {
        Iterable<Truck> trucks = truckRepo.findAll();
        trucks.forEach(t -> t.setMenu(null));
        return trucks;
    }

    public List<Truck> findByName(String name){
        List<Truck> trucks = this.truckRepo.findByNameIgnoreCaseContaining(name);
        trucks.forEach(t -> t.setMenu(null));
        return trucks;
    }

    public List<Truck> searchAdvanced(TruckDTO truck) {
        int NUM_RECS = 50;

        if(truck == null) {
            throw new RuntimeException("Invalid object given");
        }

        Iterable<Truck> suggestions = truckRepo.findAll();
        suggestions.forEach(t -> t.setMenu(null));


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
        trucks.forEach(t -> t.setMenu(null));
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
        iter.forEach(t -> t.setMenu(null));

        return StreamSupport.stream(iter.spliterator(), false)
                        .collect(Collectors.toList());
    }



    public List<Truck> getNearbyTrucks(Location loc, Integer distance) {

        //If no distance is given we will default to a 20 mile radius
        if(distance == null) {
            distance = 20;
        }

        List<Truck> allTrucks = (List<Truck>) this.truckRepo.findAll();
        allTrucks.forEach(t -> t.setMenu(null));


        List<Truck> inRange = new ArrayList<>();
        for(Truck t: allTrucks) {
            List<Route> truckRoutes = t.getRoute();
            for(Route r: truckRoutes) {
                if(checkDistance(loc, r.getLocation(), distance)) {
                    inRange.add(t);
                    break;
                }
            }
        }

        return inRange;
    }


    private Boolean checkDistance(Location locGiven, Location locTest, Integer distance) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(locGiven.getLongitude());
        double lon2 = Math.toRadians(locTest.getLongitude());
        double lat1 = Math.toRadians(locGiven.getLatitude());
        double lat2 = Math.toRadians(locTest.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 3956;


        if(( c * r ) <= (double)distance) {
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
