package jeaps.foodtruck.common.user.owner;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Owner class holds the ID of an owner
 */
@Entity
public class Owner {

    //ID of the owner
    @Id
    private Integer id;

    //The set of trucks that the Owner possesses, linked to the truck database table
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private List<Truck> trucks = new ArrayList<>();

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
    public List<Truck> getTrucks() {
        return trucks;
    }

    /**
     * Assigns the Owner a set of owned trucks
     * @param trucks The trucks to be assigned to the Owner
     */
    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }
}
