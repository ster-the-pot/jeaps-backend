package jeaps.foodtruck.common.user.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * A class to interact with the Customer table in the database
 */
@Repository
public class CustomerDAO {

    //The repository used to store Customer objects
    @Autowired
    private CustomerRepository customerRepo;

    /**
     * Saves the Customer object in the database
     * @param c The Customer object to be saved
     */
    public void save(Customer c){
        this.customerRepo.save(c);
    }

    /**
     * Saves a Customer object with the given ID in the database
     * @param id The ID of the Customer to be saved
     */
    public void save(Integer id){
        //Creates the Customer and sets the ID
        Customer c = new Customer();
        c.setId(id);

        //Saves the customer in the database
        this.customerRepo.save(c);

    }

}

