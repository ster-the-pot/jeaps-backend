package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Repository
public class TruckDAO {
    @Autowired
    private TruckRepository truckRepo;

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



    public void deleteById(Integer truckID) {
        this.truckRepo.deleteById(truckID);
    }

    public Iterable<Truck> getAllTrucks() {
        return this.truckRepo.findAll();
    }

    public List<Truck> findByName(String name){

        return this.truckRepo.findByNameIgnoreCaseContaining(name);
    }

    public List<Truck> searchAdvanced(TruckDTO truck) {
        int NUM_RECS = 50;

        if(truck == null) {
            throw new RuntimeException("Invalid object given");
        }

        Iterable<Truck> suggestions = truckRepo.findAll();

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
        if(truck.getFood() != null && truck.getFood() == t.getFood()) {
            score = score + 1;
        }
        return score;
    }




    public List<Truck> findByOwner(String username) {
        Integer id = this.userDAO.findByUsername(username).getId();
        return this.truckRepo.findByOwner_id(id);
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


    public Optional<Truck> findById(Integer id) { return this.truckRepo.findById(id); }

    public List<Truck> findALL() {
        Iterable<Truck> iter =  this.truckRepo.findAll();
        return StreamSupport.stream(iter.spliterator(), false)
                        .collect(Collectors.toList());
    }

    // to find trucks within x distance
    public List<Truck> findALLWithin(Integer distance){

        return null;
    }


    public List<Truck> getNearbyTrucks(Location loc, Integer distance) {

        //WHAT SHOULD OUR DEFAULT BE? IS THIS MILES, what???
        if(distance == null) {
            distance = 500;
        }

        List<Truck> allTrucks = (List<Truck>) this.truckRepo.findAll();
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

        Double calculation = Math.pow((locTest.getLatitude()-locGiven.getLatitude()), 2.0)
                + Math.pow((locTest.getLongitude()-locGiven.getLongitude()), 2.0);
        if(calculation <= (double)distance) {
            return true;
        }
        return false;
    }
}
