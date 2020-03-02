package jeaps.foodtruck.common.user.owner;

import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDTO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerDAO {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OwnerRepository ownerRepo;

    public void save(Owner o){
        this.ownerRepo.save(o);
    }

    public void save(Integer id){
        Owner o = new Owner();
        o.setId(id);

        this.ownerRepo.save(o);

    }

    public Boolean saveTruck(TruckDTO truckDTO, UserDTO userDTO){
        //How are we Determining the Owner??? TOKEN maybe
        User user = this.userRepo.findByUsername(userDTO.getUsername());

        //Owner owner = this.ownerRepo.findById(user.getId());

        /*if(owner == null || owner.getId() == null) {
            return false;
        }*/
        Truck t = new Truck();
        t.setName(truckDTO.getName());
        t.setRoute(truckDTO.getRoute());
        t.setMenu(truckDTO.getMenu());
        t.setType(truckDTO.getType());


        //this.truckRepo.save(t);
        return true;
    }

}
