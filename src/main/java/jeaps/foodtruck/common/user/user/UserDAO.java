package jeaps.foodtruck.common.user.user;

import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDTO;
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
    public void update(UserDTO u){
        //Find the user based on the username... They cannot change their username
        User user = this.userRepo.findByUsername(u.getUsername());
        if(u.getEmail() != null) {
            user.setEmail(u.getEmail());
        }
        if(u.getName() != null) {
            user.setName(u.getName());
        }

        //Should you be able to update the password?
        if(u.getPassword() != null) {
            user.setPassword(u.getPassword());
        }

        //Save with the id will auto update to the new user info
        this.userRepo.save(user);
    }

    public User findByUsername(String username){
        return this.userRepo.findByUsername(username);
    }



}


