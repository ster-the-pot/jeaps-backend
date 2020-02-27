package jeaps.foodtruck.common.user.customer;

import jeaps.foodtruck.common.user.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
};
