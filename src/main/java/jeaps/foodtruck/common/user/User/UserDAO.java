package jeaps.foodtruck.common.user.User;

import jeaps.foodtruck.common.user.customer.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {


    @Autowired
    private UserRepository userRepo;


    public void save(User u){
        this.userRepo.save(u);
    }

    public Integer save(UserDTO u){
        User user = new User();
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        this.userRepo.save(user);
        return user.getId();
    }

    public User findByUsername(String username){
        return this.userRepo.findByUsername(username);
    }
}



interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
};