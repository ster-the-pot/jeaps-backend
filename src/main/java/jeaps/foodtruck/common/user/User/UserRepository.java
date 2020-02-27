package jeaps.foodtruck.common.user.User;


import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
};