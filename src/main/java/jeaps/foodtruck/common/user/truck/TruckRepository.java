package jeaps.foodtruck.common.user.truck;


import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TruckRepository extends CrudRepository<Truck, Integer> {
    Truck findByName(String name);
    Truck findByType(String type);
    List<Truck> findByOwner_id(Integer id);
}
