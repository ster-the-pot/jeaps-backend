package jeaps.foodtruck.common.user.owner;


import jeaps.foodtruck.common.truck.Truck;

import javax.persistence.*;
import java.util.Set;

/**
 * Owner class holds the ID of an owner
 */
@Entity
public class Owner {

    //ID of the owner
    @Id
    private Integer id;

    //The set of trucks that the Owner possesses, linked to the truck database table
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Set<Truck> trucks;

    /**
     * Returns the ID of the Owner
     * @return the ID of the Owner
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the Owner
     * @param id the ID to be set for the Owner
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns all of the trucks that are possessed by this Owner
     * @return a set of Truck objects
     */
    public Set<Truck> getTrucks() {
        return trucks;
    }

    /**
     * Assigns the Owner a set of owned trucks
     * @param trucks The trucks to be assigned to the Owner
     */
    public void setTrucks(Set<Truck> trucks) {
        this.trucks = trucks;
    }
}
