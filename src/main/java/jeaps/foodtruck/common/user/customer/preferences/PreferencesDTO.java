package jeaps.foodtruck.common.user.customer.preferences;


public class PreferencesDTO {


    private Integer id;



    private String proxPref;

    private Integer maxPricePref;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
