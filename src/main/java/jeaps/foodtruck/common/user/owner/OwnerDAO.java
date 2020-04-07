package jeaps.foodtruck.common.user.owner;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.route.RouteDTO;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.notification.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * A class to interact with the Owner table in the database
 */
@Repository
public class OwnerDAO {

    //Used to get information about the Owner from the user table
    @Autowired
    private UserDAO userDAO;
    //The repository used to store Owner objects
    @Autowired
    private OwnerRepository ownerRepo;
    //Used to edit the trucks possessed by the owner
    @Autowired
    private TruckDAO truckDAO;

    /**
     * Saves an Owner object in the database
     * @param o The owner to be saved in the database
     */
    public void save(Owner o){
        this.ownerRepo.save(o);
    }

    /**
     * Saves an Owner in the database with the given ID
     * @param id The ID of the owner to be saved in the database
     */
    public void save(Integer id){
        //Create the Owner and set the ID
        Owner o = new Owner();
        o.setId(id);

        //Save the Owner in the database
        this.ownerRepo.save(o);

    }
    public Map<String, Object> getOwnerStats(Integer truckId) {
        Optional<Truck> truck = this.truckDAO.findById(truckId);
        Optional<User> user = this.userDAO.findById(truck.get().getOwner().getId());
        return getOwnerStats(user.get().getUsername());
    }

    public Map<String, Object> getOwnerStats(String username) {

        Map<String, Object> map = new HashMap<>();

        User user = this.userDAO.findByUsername(username);
        Optional<Owner> owner = this.ownerRepo.findById(user.getId());
        if(owner.isPresent()) {
            List<Truck> trucks = owner.get().getTrucks();

            Set<Customer> subscribed = new HashSet<>();
            Set<Food> foodtypes = new HashSet<>();

            Double average = null;
            Integer num = 0;
            for (Truck t : trucks) {
                if(t.getAvgRating() != null){
                    if(average == null) {
                        average = t.getAvgRating();
                    } else {
                        average = average + t.getAvgRating();
                    }
                    num++;
                }

                foodtypes.addAll(t.getFood());
                subscribed.addAll(t.getCustomers());
            }
            if(average != null) {
                average = (average / ((double) num));
            }

            map.put("Trucks", trucks.size());
            map.put("Subscribers", subscribed.size());
            map.put("FoodTypes", foodtypes.size());
            map.put("AvgTruckRating", average);
        }

        return map;
    }

    /**
     *
     *
     * @param truckDTO
     * @param username
     * @return
     */
    public Boolean saveTruck(TruckDTO truckDTO, String username){

        //Gets the user information given the username
        User user = this.userDAO.findByUsername(username);

        //Check if the user is an owner
        Optional<Owner> owner = this.ownerRepo.findById(user.getId());

        //If the user is not an owner
        if(!owner.isPresent()) {
            return false;
        }

        //Creates and sets the information of the truck
        //Truck t = new Truck(truckDTO.getName(), truckDTO.getRoute(), truckDTO.getType().name(), truckDTO.getMenu());

        Truck t = new Truck();
        t.setMenu(truckDTO.getMenu());
        t.setName(truckDTO.getName());
        t.setFood(truckDTO.getFood());
        t.setPrice(truckDTO.getPrice());


        List<Truck> list = owner.get().getTrucks();
        list.add(t);
        owner.get().setTrucks(list);

        save(owner.get());


        //this.truckRepo.save(t);
        return true;
    }
    public void editTruck(TruckDTO truckDTO, Integer truckID) {
        Optional<Truck> t =  truckDAO.findById(truckID);

        Owner owner = t.get().getOwner();

        List<Truck> trucks = owner.getTrucks();

        trucks.remove(t);


        if(truckDTO.getMenu() != null) {
            t.get().setMenu(truckDTO.getMenu());
        }
        if(truckDTO.getFood() != null) {
            t.get().setFood(truckDTO.getFood());
        }
        if(truckDTO.getName() != null) {
            t.get().setName(truckDTO.getName());
        }
        if(truckDTO.getPrice() != null) {
            t.get().setPrice(truckDTO.getPrice());
        }

        trucks.add(t.get());

        owner.setTrucks(trucks);
        this.save(owner);

    }

    public void deleteTruck(Integer truckID) {


        Optional<Truck> truck = truckDAO.findById(truckID);
        Owner owner = truck.get().getOwner();

        List<Truck> trucks = owner.getTrucks();
        trucks.remove(truck.get());

        owner.setTrucks(trucks);
        this.save(owner);


        this.truckDAO.deleteById(truckID);
    }
    /**
     * Finds if an owner exists with the following ID
     * @param id The id of the owner to find
     * @return An Owner if it exists, otherwise an empty Optional
     */
    public Optional<Owner> findById(Integer id) { return this.ownerRepo.findById(id); }


    public Iterable<Owner> findAll() { return this.ownerRepo.findAll(); }




    public void setOwnerRepo(OwnerRepository ownerRepo) {
        this.ownerRepo = ownerRepo;
    }
}
