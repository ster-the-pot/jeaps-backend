package jeaps.foodtruck.common.user.customer.preferences;


import jeaps.foodtruck.common.truck.Prices;
import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.route.Location;

import java.util.List;

public class PreferencesDTO {


    private Integer id;


    private List<Food> food;

    private String proxPref;

    private Prices maxPricePref;

    private Location location;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
