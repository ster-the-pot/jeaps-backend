package jeaps.foodtruck.common.user.customer;

import org.springframework.data.repository.CrudRepository;



public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    //User findByUsername(String username);
};