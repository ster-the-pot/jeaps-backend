package jeaps.foodtruck.common.user.customer.preferences;


import jeaps.foodtruck.common.truck.FoodTypes;
import jeaps.foodtruck.common.truck.Prices;
import jeaps.foodtruck.common.truck.route.Location;

import javax.persistence.*;


@Entity
public class Preferences {


    @Id
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    //@ManyToOne
    private FoodTypes foodPref;

    private String proxPref;

    @Enumerated(EnumType.ORDINAL)
    private Prices maxPricePref;

    @Embedded
    private Location location;



    public Preferences(){};
    public Preferences(Integer id) {
        this.id = id;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
