package jeaps.foodtruck.common.user.owner;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.route.RouteDTO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        //CONT>>>>>>>>>> FIX ALL


        List<Truck> list = owner.get().getTrucks();
        list.add(t);
        owner.get().setTrucks(list);

        save(owner.get());


        //this.truckRepo.save(t);
        return true;
    }

    /**
     * Finds if an owner exists with the following ID
     * @param id The id of the owner to find
     * @return An Owner if it exists, otherwise an empty Optional
     */
    public Optional<Owner> findById(Integer id) { return this.ownerRepo.findById(id); }


    public Iterable<Owner> findAll() { return this.ownerRepo.findAll(); }
    /**
     *
     * @param routeDTO
     * @param truckID
     * @param username
     * @return
     */
    public Boolean saveRoute(RouteDTO routeDTO, Integer truckID, String username){

        User user = this.userDAO.findByUsername(username);

        Optional<Owner> owner = this.ownerRepo.findById(user.getId());

        if(!owner.isPresent()) {
            return false;
        }

        Optional<Truck> t =  this.truckDAO.findById(truckID);
        if(!t.isPresent()) {
            return false;
        }

        List<Truck> trucks = owner.get().getTrucks();

        if(!trucks.contains(t.get())) {
            return false;
        } else {
            trucks.remove(t.get());
        }

        Route route = new Route();
        route.setDate(routeDTO.getDate());
        //route.setLocation(routeDTO.getLocation());
        //SET STUFF HERE

        List<Route> r = t.get().getRoute();
        r.add(route);
        t.get().setRoute(r);

        trucks.add(t.get());


        owner.get().setTrucks(trucks);

        //this.truckDAO.update(t.get());

        this.save(owner.get());

        return true;
    }


    public void setOwnerRepo(OwnerRepository ownerRepo) {
        this.ownerRepo = ownerRepo;
    }
}
