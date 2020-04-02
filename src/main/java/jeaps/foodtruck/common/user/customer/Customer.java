package jeaps.foodtruck.common.user.customer;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer class holds an ID of a user that is a customer.
 */
@Entity
public class Customer {

    //The customer's ID
    @Id
    private Integer id;


    //@OneToOne(cascade = CascadeType.ALL)
    //@PrimaryKeyJoinColumn
    //private Preferences preference;
    /*@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Preferences preference;*/

    /*public Customer(){};
    public Customer(Integer id) {
        this.id = id;
        preference = new Preferences(id);
    }*/

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

    /**
     * Sets the customer's ID
     * @param id The ID to be set for the customer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*public Preferences getPreference(){
        return this.preference;
    }

    public void setPreference(Preferences preference) {
        this.preference = preference;
    }*/
}
