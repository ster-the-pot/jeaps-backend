package jeaps.foodtruck.common.user.customer;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Customer class holds an ID of a user that is a customer.
 */
@Entity
public class Customer {

    //The customer's ID
    @Id
    private Integer id;

    /**
     * Returns the customer's ID
     * @return the customer's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the customer's ID
     * @param id The ID to be set for the customer
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
