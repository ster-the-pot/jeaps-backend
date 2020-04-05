package jeaps.foodtruck.common.truck.food;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jeaps.foodtruck.common.truck.Truck;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {

    public Food(){
        this.foodtype = null;
    }

    public Food(String type){
        this.foodtype = type;
    }
    @Id
    private String foodtype;

    public String getFoodtype() { return foodtype; }

    public void setFoodtype(String foodtype) { this.foodtype = foodtype; }
}