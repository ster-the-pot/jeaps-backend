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
        Truck t = new Truck();

        t.setName(truckDTO.getName());

        this.truckRepo.save(t);

    }



}
