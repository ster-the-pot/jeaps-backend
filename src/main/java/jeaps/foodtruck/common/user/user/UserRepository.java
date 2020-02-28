package jeaps.foodtruck.common.user.user;


import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
};