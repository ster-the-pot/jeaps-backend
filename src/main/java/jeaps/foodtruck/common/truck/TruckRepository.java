package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.food.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TruckRepository extends CrudRepository<Truck, Integer> {
    List<Truck> findByName(String name);
    List<Truck> findByNameIgnoreCaseContaining(String part);
    List<Truck> findByFood(Food food);
    List<Truck> findByPrice(Prices price);



    List<Truck> findByOwner_id(Integer id);
}