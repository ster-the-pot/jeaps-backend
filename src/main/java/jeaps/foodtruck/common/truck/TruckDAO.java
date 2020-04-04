package jeaps.foodtruck.common.truck;

import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.rate.RateDAO;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Repository
public class TruckDAO {
    @Autowired
    private TruckRepository truckRepo;
    /*@Autowired
    private CustomerDAO customerDAO;*/
    @Autowired
    private RateDAO rateDAO;
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

    public void update(TruckDTO truckDTO, Integer truckID) {

        Optional<Truck> t = this.truckRepo.findById(truckID);
        //WE SHOULD BE ABLE TO CHANGE THE TRUCK NAME.....
        t.get().setName(truckDTO.getName());
        /*if(truckDTO.getRoute() != null) {
            t.get().setRoute(truckDTO.getRoute());
        }*/
        if(truckDTO.getMenu() != null) {
            t.get().setMenu(truckDTO.getMenu());
        }
        if(truckDTO.getType() != null) {
            t.get().setType(truckDTO.getType());
        }
        if(truckDTO.getName() != null) {
            t.get().setName(truckDTO.getName());
        }

        this.truckRepo.save(t.get());

    }

    public void delete(Integer truckID) {
        this.truckRepo.deleteById(truckID);
    }



    public Iterable<Truck> getAllTrucks() {
        return this.truckRepo.findAll();
    }


    public Truck findByName(String name){
        return this.truckRepo.findByName(name);
    }

    public Truck findByType(String type){
        return this.truckRepo.findByType(type);
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


    public List<Object> findByOwnerPlus(String username) {
        List<Object> returns = new ArrayList<>();

        Integer id = this.userDAO.findByUsername(username).getId();
        List<Truck> trucks = this.truckRepo.findByOwner_id(id);


        List<Object> userInfo = new ArrayList<>();
        userInfo.add(id);
        userInfo.add(username);
        returns.add(userInfo);

        returns.add(trucks);

        return returns;
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

    /*************************************************************************
     * Begin Rating Section
     *************************************************************************/
    public List<Rate> getRatingsObject(Integer truck_id) {

        Optional<Truck> truck = truckRepo.findById(truck_id);

        if(truck.isPresent()) {
            return truck.get().getRate();
        }
        return null;
    }
    public List<Integer> getRatings(Integer truck_id) {
        Optional<Truck> truck = truckRepo.findById(truck_id);
        List<Integer> rates = new ArrayList<>();

        if(truck.isPresent()) {
            for(Rate r: truck.get().getRate()) {
                rates.add(r.getRate());
            }
        }
        return rates;
    }

    public void addRate(RateDTO r, Integer truck_id, String username) {
        Integer customer_id = this.userDAO.findByUsername(username).getId();
        Rate rate = new Rate();
        /*Optional<Customer> c = customerDAO.findByID(customer_id);
        Optional<Truck> truck = truckRepo.findById(truck_id);
        rate.setCustomer(c.get());
        rate.setTruck(truck.get());
        rate.setBody(r.getBody());
        rate.setRate(r.getRate());
        rate.setSubject(r.getSubject());

        //If a truck/customer rating does not yet exist then it should be added
        if(rateDAO.findByTruckAndCustomer(truck_id, customer_id) == null) {
            List<Rate> rates = truck.get().getRate();
            rates.add(rate);
            truck.get().setRate(rates);

            //Set the new average rating for the truck
            Integer tempTotal = null;
            for(Rate tempRate: truck.get().getRate()) {
                tempTotal = tempTotal + tempRate.getRate();
            }

            truck.get().setAvgRating((tempTotal / ((double) truck.get().getRate().size())));

            truckRepo.save(truck.get());
        } else {
            throw new RuntimeException("Rating between customer and truck already exists");
        }*/
    }
    public void editRate(RateDTO r, Integer truck_id, String username) {
        Integer customer_id = this.userDAO.findByUsername(username).getId();
        Rate rate = new Rate();
        /*Optional<Customer> c = customerDAO.findByID(customer_id);
        Optional<Truck> truck = truckRepo.findById(truck_id);
        rate.setCustomer(c.get());
        rate.setTruck(truck.get());
        rate.setBody(r.getBody());
        rate.setRate(r.getRate());
        rate.setSubject(r.getSubject());

        //If a truck/customer rating does not yet exist then it should be added
        Rate oldRate;
        if((oldRate = rateDAO.findByTruckAndCustomer(truck_id, customer_id)) != null) {
            List<Rate> rates = truck.get().getRate();

            rates.remove(oldRate);
            rates.add(rate);
            truck.get().setRate(rates);

            //Set the new average rating for the truck
            Integer tempTotal = null;
            for(Rate tempRate: truck.get().getRate()) {
                tempTotal = tempTotal + tempRate.getRate();
            }

            truck.get().setAvgRating((tempTotal / ((double) truck.get().getRate().size())));

            truckRepo.save(truck.get());
        } else {
            throw new RuntimeException("Rating between customer and truck does not exist");
        }*/
    }

    public void removeRate(Integer truck_id, String username) {
        Integer customer_id = this.userDAO.findByUsername(username).getId();
        Rate rate = rateDAO.findByTruckAndCustomer(truck_id, customer_id);
        if (rate == null) {
            throw new RuntimeException("Rating between customer and truck does not exist");
        }
        Optional<Truck> truck = truckRepo.findById(truck_id);
        if(truck.isPresent()) {
            List<Rate> rates = truck.get().getRate();
            rates.remove(rate);

            truck.get().setRate(rates);
            truckRepo.save(truck.get());
        }
    }

    public double getAvgRating(Integer id) {
        Optional<Truck> truck = truckRepo.findById(id);
        if(truck.isPresent()){
            return truck.get().getAvgRating();
        }
        throw new RuntimeException("Rating average does not yet exist");
    }
    /*************************************************************************
     * End Rating Section
     *************************************************************************/


    /*public void setTruckRepo(TruckRepository truckRepo) {
        this.truckRepo = truckRepo;
    }*/

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
