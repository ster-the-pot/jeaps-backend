package jeaps.foodtruck.common.truck.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDAO {
    @Autowired
    FoodRepository foodRepo;

    public Iterable<Food> getAllFood() { return foodRepo.findAll();}


    public void saveFood(String food) {
        Food temp = foodRepo.findByFoodtype(food);
        if(temp != null) {
            throw new RuntimeException("Food type is already in database");
        }
        Food f = new Food();
        f.setFoodtype(food);
        foodRepo.save(f);
    }

    public void saveAllFood(List<String> food) {
        Food f = new Food();
        for(String foodtemp: food) {
            Food temp = foodRepo.findByFoodtype(foodtemp);
            if(temp == null) {
                f.setFoodtype(foodtemp);
                foodRepo.save(f);
            }
        }

    }

    public void deleteFood(String food) {

        Food temp = foodRepo.findByFoodtype(food);
        if(temp == null) {
            throw new RuntimeException("Food does not exist");
        }
        Food f = new Food();
        f.setFoodtype(food);
        foodRepo.delete(f);
    }

    public Iterable<Food> displayFood() {
        return foodRepo.findAll();
    }
}
