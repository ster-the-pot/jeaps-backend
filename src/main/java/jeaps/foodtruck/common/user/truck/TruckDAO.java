package jeaps.foodtruck.common.user.truck;

import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TruckDAO {
    @Autowired
    private TruckRepository truckRepo;

    public void save(Truck t){
        this.truckRepo.save(t);
    }

    public void save(TruckDTO truckDTO){
        //How are we Determining the Owner??? TOKEN maybe
        Truck t = new Truck();
        t.setName(truckDTO.getName());
        t.setRoute(truckDTO.getRoute());
        t.setMenu(truckDTO.getMenu());
        t.setType(truckDTO.getType());

        this.truckRepo.save(t);

    }
    public void update(TruckDTO truckDTO){

        Truck t = this.truckRepo.findByName(truckDTO.getName());
        //WE SHOULD BE ABLE TO CHANGE THE TRUCK NAME.....
        //t.setName(truckDTO.getName());
        if(truckDTO.getRoute() != null) {
            t.setRoute(truckDTO.getRoute());
        }
        if(truckDTO.getMenu() != null) {
            t.setMenu(truckDTO.getMenu());
        }
        if(truckDTO.getType() != null) {
            t.setType(truckDTO.getType());
        }

        this.truckRepo.save(t);

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
    public Truck findByOwner(String owner){
        return this.truckRepo.findByOwner_id(owner);
    }

}
