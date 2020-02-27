package jeaps.foodtruck.common.user.customer;


import jeaps.foodtruck.common.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDAO {


    @Autowired
    private CustomerRepository userRepo;


    public void save(User u){
        this.userRepo.save(u);
    }

    public void save(CustomerDTO u){
        User user = new User();
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        this.userRepo.save(user);

    }

    public User findByUsername(String username){
        return this.userRepo.findByUsername(username);
    }
}
