package jeaps.foodtruck.common.user.customer.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FoodDAO {
    @Autowired
    FoodRepository foodRepo;

    public Iterable<Food> getAllFood() { return foodRepo.findAll();}

    public void saveFood(Food food) { foodRepo.save(food); }

    public void saveFood(FoodDTO food) {
        Food f = new Food();
        f.setFoodtype(food.getFoodtype());

        foodRepo.save(f);
    }

    public void saveFood(String food) {
        Food f = new Food();
        f.setFoodtype(food);

        foodRepo.save(f);
    }
}
