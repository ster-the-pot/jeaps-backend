package jeaps.foodtruck.common.truck;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TruckRepository extends CrudRepository<Truck, Integer> {
    Truck findByName(String name);
    List<Truck> findByOwner_id(Integer id);
}