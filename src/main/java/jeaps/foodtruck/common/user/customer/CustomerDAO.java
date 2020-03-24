package jeaps.foodtruck.common.user.customer;


import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to interact with the Customer table in the database
 */
@Repository
public class CustomerDAO {

    //The repository used to store Customer objects
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private UserDAO userDAO;
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

    public List<Truck> getRecomendations(String username) {
        //Initialise the list of trucks to return
        List<Truck> suggestions = new ArrayList<Truck>();

        //Get the user info that we need
        User user = userDAO.findByUsername(username);



        return suggestions;
    }
}

