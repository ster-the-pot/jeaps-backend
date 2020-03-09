package jeaps.foodtruck.common.user.customer;

import javax.persistence.Id;

/**
 * Class used as a template for obtaining Customer objects from JSON
 */
public class CustomerDTO {

    //ID of the customer
    @Id
    private Integer id;

    /**
     * Used to get the ID from the generated Customer
     * @return the ID from the generated Customer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Used to set the Customer's ID
     * @param id the ID to be set for the Customer
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
