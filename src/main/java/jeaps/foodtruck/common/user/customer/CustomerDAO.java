package jeaps.foodtruck.common.user.customer;


import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.common.user.customer.preferences.PreferencesDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * A class to interact with the Customer table in the database
 */
@Repository
public class CustomerDAO {

    //Constants to use with getting recommendations
    private static Integer NUM_RECS = 10;
    private static Integer MAX_DISTANCE = 30;
    private static Integer DISTANCE_FILTER = 10;

    //The repository used to store Customer objects
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TruckDAO truckDAO;

    @Autowired
    private PreferencesDAO preferencesDAO;

    /**
     * Saves the Customer object in the database
     * @param c The Customer object to be saved
     */
    public void save(Customer c) {
        this.customerRepo.save(c);
    }

    /**
     * Saves a Customer object with the given ID in the database
     * @param id The ID of the Customer to be saved
     */
    public void save(Integer id){
        //Creates the Customer and sets the ID
        Customer c = new Customer();
        c.setId(id);
        //c.preference = new Preferences(id);
        //Saves the customer in the database
        this.save(c);

    }
    public Iterable<Customer> findAll() { return this.customerRepo.findAll(); }

    public Optional<Customer> findByID(Integer id) { return this.customerRepo.findById(id); }

    public List<Truck> getSubscribedTrucks(String username) {
        List<Object> returns = new ArrayList<>();
        User user = userDAO.findByUsername(username);
        Optional<Customer> customer = customerRepo.findById(user.getId());


            return customer.get().getTrucks();

    }

    public List<Object> getPreferences(String username) {
        List<Object> returns = new ArrayList<>();
        User user = userDAO.findByUsername(username);

        List<Object> userInfo = new ArrayList<>();
        userInfo.add(user.getId());
        userInfo.add(user.getUsername());
        userInfo.add(user.getEmail());
        returns.add(userInfo);

        Optional<Customer> customer = customerRepo.findById(user.getId());
        Optional<Preferences> pref = preferencesDAO.findById(user.getId());
        if(customer.isPresent() && pref.isPresent()) {
            returns.add(pref.get());
        }
        return returns;
    }



    public void editPreferences(String username, Preferences pref) {

        User user = userDAO.findByUsername(username);

        Optional<Customer> customer = customerRepo.findById(user.getId());
        if(customer.isPresent()) {
           //customer.get().setPreference(pref);
            Optional<Preferences> oldPref = preferencesDAO.findById(user.getId());

            //if(!oldPref.isPresent() || pref.getId() == null) {
            pref.setId(user.getId());
            //}
            preferencesDAO.save(pref);

        }

    }

    public void subscribeToTruck(String username, Integer truckID) {
        User user = userDAO.findByUsername(username);
        Optional<Customer> customer = customerRepo.findById(user.getId());
        Optional<Truck> truck = truckDAO.findById(truckID);


        if(customer.isPresent() && truck.isPresent()) {

            //List<Customer> customers = truck.get().getCustomers();
            //customers.add(customer.get());
            //truck.get().setCustomers(customers);

            List<Truck> trucks = customer.get().getTrucks();
            if(!trucks.contains(truck.get())) {
                trucks.add(truck.get());
            }
            customer.get().setTrucks(trucks);


            customerRepo.save(customer.get());


        } //HOW ARE WE THROWING ERRORS AGAIN?????
    }

    public void unsubscribeToTruck(String username, Integer truckID) {
        User user = userDAO.findByUsername(username);
        Optional<Customer> customer = customerRepo.findById(user.getId());
        Optional<Truck> truck = truckDAO.findById(truckID);


        if(customer.isPresent() && truck.isPresent()) {


            //List<Customer> customers = truck.get().getCustomers();
            //customers.remove(customer.get());
            //truck.get().setCustomers(customers);

            List<Truck> trucks = customer.get().getTrucks();
            if(trucks.contains(truck.get())) {
                trucks.remove(truck.get());
            }
            customer.get().setTrucks(trucks);


            customerRepo.save(customer.get());
        } //HOW ARE WE THROWING ERRORS AGAIN?????
    }





    public List<Truck> getRecommendations(String username) {
        //Initialise the list of trucks to return
        List<Truck> suggestions = new ArrayList<Truck>();
        //Get the user who we are providing recommendations for
        User user = userDAO.findByUsername(username);
        //Get the preferences of the user
        Optional<Preferences> userPrefs = preferencesDAO.findById(user.getId());
        //Get all trucks within a set distance                  *********right now there is no distance calculation***********

        suggestions = truckDAO.findALL();
        //If there are no preferences, return a random set of trucks
        if(!userPrefs.isPresent()){
            if(suggestions.size() > NUM_RECS){
                return suggestions.subList(0, NUM_RECS-1);
            }
            else{
                return suggestions;
            }
        }
        //Create a map to sort trucks based on scores
        Map<Integer, List<Truck>> truckScores = new HashMap<Integer, List<Truck>>();
        int highscore = 0;
        for(Truck t : suggestions){
            int score = getScore(t, userPrefs.get(), user.getId());
            if(score > highscore){highscore = score;}

            //If the truck does not contain the score, add it
            if(!truckScores.containsKey(score)){
                truckScores.put(score, new ArrayList<Truck>());
            }
            //Add the truck to the right score bracket
            truckScores.get(score).add(t);
        }
        suggestions = new ArrayList<Truck>();

        while(highscore >= 0 && suggestions.size() < NUM_RECS){
            if(truckScores.get(highscore) != null){
                for(Truck t : truckScores.get(highscore)){
                    if(suggestions.size() < NUM_RECS){
                        suggestions.add(t);
                    }
                }
            }
            highscore--;
        }
        return suggestions;
    }

    public Integer getScore(Truck truck, Preferences prefs, Integer id){
        int score = 0;
        //increase truck score if the food is preferred
        if(truck.getFood() != null && truck.getFood() == prefs.getFood()){
            score += 10;
        }
        //increase truck score if the price is within budget
        if(truck.getPrice() != null && prefs.getMaxPricePref() != null && truck.getPrice().getFloor() <= prefs.getMaxPricePref().getFloor()){
            score += 20;
        }
        //increase truck score based on rating
        score += 10*truck.getAvgRating();
        //reduce truck score if the customer is subscribed to the truck
        Customer c = new Customer();
        c.setId(id);
        if(c.getTrucks() != null && c.getTrucks().contains(truck)){
            score -= 30;
        }
        return score;
    }


}

