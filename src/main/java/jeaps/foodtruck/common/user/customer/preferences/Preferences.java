package jeaps.foodtruck.common.user.customer.preferences;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.FoodTypes;
import jeaps.foodtruck.common.truck.Prices;
import jeaps.foodtruck.common.user.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
public class Preferences {


    @Id
    @Column(name = "customer_id")
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    //@ManyToOne
    private FoodTypes foodPref;

    private String proxPref;

    @Enumerated(EnumType.ORDINAL)
    private Prices maxPricePref;


    @MapsId
    @OneToOne(mappedBy = "preference")
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;





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
