package jeaps.foodtruck.common.user.customer;

import org.springframework.data.repository.CrudRepository;


/**
 * Repository of Customer objects in the database
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    //User findByUsername(String username);
};