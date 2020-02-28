package jeaps.foodtruck.common.user.truck;


import org.springframework.data.repository.CrudRepository;


public interface TruckRepository extends CrudRepository<Truck, Integer> {
    Truck findByName(String name);
    Truck findByType(String type);
    //Truck findByOwner_id(String owner_id);

}
