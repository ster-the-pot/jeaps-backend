package jeaps.foodtruck.common.user.customer.preferences;


import jeaps.foodtruck.common.truck.FoodTypes;
import jeaps.foodtruck.common.truck.Prices;
import jeaps.foodtruck.common.truck.route.Location;

public class PreferencesDTO {


    private Integer id;


    private FoodTypes foodPref;

    private String proxPref;

    private Prices maxPricePref;

    private Location location;


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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
