package jeaps.foodtruck.common.user.customer;


import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.customer.food.Food;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;

import javax.persistence.*;
import java.util.Set;

/**
 * Customer class holds an ID of a user that is a customer.
 */
@Entity
public class Customer {

    //The customer's ID
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    Preferences preference;


    /**
     * Returns the customer's ID
     * @return the customer's ID
     */
    public Integer getId() {
        return id;
    }

    public Set<Truck> getTrucks() { return trucks; }

    public void setTrucks(Set<Truck> trucks) { this.trucks = trucks; }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Set<Truck> trucks;

    /**
     * Sets the customer's ID
     * @param id The ID to be set for the customer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Preferences getPreference(){
        return this.preference;
    }
}
