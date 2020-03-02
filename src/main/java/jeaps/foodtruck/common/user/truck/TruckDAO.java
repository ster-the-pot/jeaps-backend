package jeaps.foodtruck.common.user.truck;

import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public void delete(String truckID) {
        this.truckRepo.deleteById(Integer.parseInt(truckID));
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

    public Optional<Truck> findById(Integer id) { return this.truckRepo.findById(id); }

    public List<Truck> findALL() {
        Iterable<Truck> iter =  this.truckRepo.findAll();
        return StreamSupport.stream(iter.spliterator(), false)
                        .collect(Collectors.toList());
    }



}
