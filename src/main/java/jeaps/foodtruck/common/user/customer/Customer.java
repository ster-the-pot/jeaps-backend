package jeaps.foodtruck.common.user.customer;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Customer class holds an ID of a user that is a customer.
 */
@Entity
public class Customer {

    //The customer's ID
    @Id
    @Column(name = "id")
    private Integer id;


    /**
     * Shows a list of subscribed trucks
     */
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name="subscribed",
            joinColumns={@JoinColumn(name = "customer_id")},
            inverseJoinColumns={@JoinColumn(name="truck_id")})
    private List<Truck> trucks = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Rate> rate = new ArrayList<>();


    /**
     * Returns the customer's ID
     * @return the customer's ID
     */
    public Integer getId() {
        return id;
    }

    public List<Truck> getTrucks() { return trucks; }

    public void setTrucks(List<Truck> trucks) { this.trucks = trucks; }

    /**
     * Sets the customer's ID
     * @param id The ID to be set for the customer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Rate> getRate() {
        return rate;
    }

    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }
}
