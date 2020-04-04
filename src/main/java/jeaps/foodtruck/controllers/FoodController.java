package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.food.FoodDAO;
import jeaps.foodtruck.common.truck.food.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/food")
@ResponseBody
public class FoodController {
    @Autowired
    FoodDAO foodDAO;

    @PostMapping(path="/addFood")
    public ResponseEntity<?> addFood(@RequestBody String food) {
        foodDAO.saveFood(food);
        return ResponseEntity.ok("Successfully added a Food type");
    }
    @PostMapping(path="/addListFood")
    public ResponseEntity<?> addListFood(@RequestBody List<String> food) {
        foodDAO.saveAllFood(food);
        return ResponseEntity.ok("Successfully added a List of Food types");
    }
    @PostMapping(path="/deleteFood")
    public ResponseEntity<?> deleteFood(@RequestBody String food) {
        foodDAO.deleteFood(food);
        return ResponseEntity.ok("Successfully deleted a Food type");
    }

    @RequestMapping(path="/display", method = RequestMethod.GET)
    public ResponseEntity<?> display(){
        Map<String, Object> map = new HashMap<>();
        map.put("Food", foodDAO.displayFood());
        return ResponseEntity.created(URI.create("/display/done")).body(map);
    }

}
