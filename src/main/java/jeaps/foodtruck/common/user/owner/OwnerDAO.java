package jeaps.foodtruck.common.user.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerDAO {


    @Autowired
    private OwnerRepository userRepo;


    public void save(Owner o){
        this.userRepo.save(o);
    }

    public void save(Integer id){
        Owner o = new Owner();
        o.setId(id);

        this.userRepo.save(o);

    }
}
