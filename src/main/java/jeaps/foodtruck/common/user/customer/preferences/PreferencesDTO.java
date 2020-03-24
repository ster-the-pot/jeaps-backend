package jeaps.foodtruck.common.user.customer.preferences;

import jeaps.foodtruck.common.user.customer.food.Food;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class PreferencesDTO {

    @Id
    private Integer id;

    @ManyToOne
    private Food foodPref;

    private String proxPref;

    private Integer maxPricePref;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Food getFoodPref() {
        return foodPref;
    }

    public void setFoodPref(Food foodPref) {
        this.foodPref = foodPref;
    }

    public String getProxPref() {
        return proxPref;
    }

    public void setProxPref(String proxPref) {
        this.proxPref = proxPref;
    }

    public Integer getMaxPricePref() {
        return maxPricePref;
    }

    public void setMaxPricePref(Integer maxPricePref) {
        this.maxPricePref = maxPricePref;
    }
}
