package jeaps.foodtruck.common.user.customer.preferences;

import jeaps.foodtruck.common.truck.FoodTypes;
import jeaps.foodtruck.common.truck.Prices;
import jeaps.foodtruck.common.user.customer.food.Food;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Preferences {


    @Id
    private Integer id;

    private FoodTypes foodPref;

    private String proxPref;

    private Prices maxPricePref;

    public Preferences() {};
    public Preferences(Integer id) {
        this.setId(id);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FoodTypes getFoodPref() {
        return foodPref;
    }

    public void setFoodPref(FoodTypes foodPref) {
        this.foodPref = foodPref;
    }

    public String getProxPref() {
        return proxPref;
    }

    public void setProxPref(String proxPref) {
        this.proxPref = proxPref;
    }

    public Prices getMaxPricePref() {
        return maxPricePref;
    }

    public void setMaxPricePref(Prices maxPricePref) {
        this.maxPricePref = maxPricePref;
    }


}
