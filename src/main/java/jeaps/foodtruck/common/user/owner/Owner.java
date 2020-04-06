package jeaps.foodtruck.common.user.owner;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.user.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    /**
     * Gets all of the subscribers of the owner
     *
     * @return a Set of subscribed customers
     */
    public Set<Customer> getSubscribers(){

        //Make a set to hold the subscribers
        Set<Customer> subs = new HashSet<Customer>();
        //Add all the subs to the set
        for(Truck t : this.getTrucks()){
            subs.addAll(t.getCustomers());
        }

        return subs;
    }

    /**
     * Gets all of the types of food from the trucks the owner has
     *
     * @return a Set of food types
     */
    public Set<Food> getFoodTypes(){

        //Make a set to hold the food types
        Set<Food> foods = new HashSet<>();
        //Add all the foods to the set
        for(Truck t : this.getTrucks()){
            foods.addAll(t.getFood());
        }

        return foods;
    }

    /**
     * Gets all of the types of food from the trucks the owner has
     *
     * @return a Set of food types
     */
    public Double getAvgRating(){
        Double average = null;
        Integer num = 0;
        for (Truck t : trucks) {
            if(t.getAvgRating() != null){
                if(average == null) {
                    average = t.getAvgRating();
                } else {
                    average = average + t.getAvgRating();
                }
                num++;
            }
        }
        if(average != null) {
            average = (average / ((double) num));

            return average;
        }

        //default average to 0
        return 0.0;
    }
}
