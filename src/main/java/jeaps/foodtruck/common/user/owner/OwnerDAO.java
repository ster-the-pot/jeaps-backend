package jeaps.foodtruck.common.user.owner;

import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDAO;
import jeaps.foodtruck.common.user.truck.TruckDTO;
import jeaps.foodtruck.common.user.truck.TruckRepository;
import jeaps.foodtruck.common.user.truck.route.Route;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class OwnerDAO {


    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OwnerRepository ownerRepo;
    @Autowired
    private TruckDAO truckDAO;

    public void save(Owner o){
        this.ownerRepo.save(o);
    }

    public void save(Integer id){
        Owner o = new Owner();
        o.setId(id);

        this.ownerRepo.save(o);

    }

    public Boolean saveTruck(TruckDTO truckDTO, String username){
        //How are we Determining the Owner??? TOKEN maybe
        User user = this.userDAO.findByUsername(username);

        Optional<Owner> owner = this.ownerRepo.findById(user.getId());

        if(!owner.isPresent()) {
            return false;
        }
        Truck t = new Truck();
        t.setName(truckDTO.getName());
        t.setRoute(truckDTO.getRoute());
        t.setMenu(truckDTO.getMenu());
        t.setType(truckDTO.getType());


        Set<Truck> set = owner.get().getTrucks();
        set.add(t);
        owner.get().setTrucks(set);

        save(owner.get());


        //this.truckRepo.save(t);
        return true;
    }


    public Optional<Owner> findById(Integer id) { return this.ownerRepo.findById(id); }

    public Boolean saveRoute(Route route, String truckID, String username){

        User user = this.userDAO.findByUsername(username);

        Optional<Owner> owner = this.ownerRepo.findById(user.getId());

        if(!owner.isPresent()) {
            return false;
        }
        Optional<Truck> t =  this.truckDAO.findById(Integer.parseInt(truckID));
        if(!t.isPresent()) {
            return false;
        }

        Set<Truck> set = owner.get().getTrucks();
        if(!set.contains(t.get())) {
            return false;
        }


        Set<Route> r = t.get().getRoute();
        r.add(route);



        t.get().setRoute(r);

        this.truckDAO.update(t.get());


        //this.truckRepo.save(t);
        return true;
    }


}
