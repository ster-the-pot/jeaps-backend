package jeaps.foodtruck.common.truck.food;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, String> {
    Food findByFoodtype(String str);
}
