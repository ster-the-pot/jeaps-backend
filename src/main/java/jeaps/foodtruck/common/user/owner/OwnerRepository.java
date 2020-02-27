package jeaps.foodtruck.common.user.owner;

import jeaps.foodtruck.common.user.User.User;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
};