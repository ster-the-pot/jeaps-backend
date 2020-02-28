package jeaps.foodtruck.common.user.user;

import org.springframework.beans.factory.annotation.Autowired;
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


