package jeaps.foodtruck.common.truck.food;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.route.Route;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {
    @Id
    private String foodtype;



    public String getFoodtype() { return foodtype; }

    public void setFoodtype(String foodtype) { this.foodtype = foodtype; }
}