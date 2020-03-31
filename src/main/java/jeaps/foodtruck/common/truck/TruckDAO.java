package jeaps.foodtruck.common.truck;

import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.user.customer.Customer;
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
    @Autowired
    private UserDAO userDAO;


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


    public void setTruckRepo(TruckRepository truckRepo) {
        this.truckRepo = truckRepo;
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
