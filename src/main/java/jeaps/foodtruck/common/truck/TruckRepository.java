package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.food.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TruckRepository extends CrudRepository<Truck, Integer> {
    List<Truck> findByName(String name);
    List<Truck> findByNameIgnoreCaseContaining(String part);
    //List<Profile> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrMidNameIgnoreCaseContaining(String firstName, String lastName, String midName);
    // if(truck.getFood() != null && truck.getName() != null && truck.getPrice() != null && truck.getId() != null) {
            /*private Integer id;
        private String name;
        private String menu;
        private Prices price;
        private Food food;*/
    //List<Truck> findByNameIgnoreCaseContainingANDFoodANDPriceANDId(String part, Food food, Prices price, Integer id);
    List<Truck> findByOwner_id(Integer id);
}