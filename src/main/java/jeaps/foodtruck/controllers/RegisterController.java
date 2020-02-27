package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.User.User;
import jeaps.foodtruck.common.user.User.UserDAO;
import jeaps.foodtruck.common.user.User.UserDTO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.customer.CustomerDTO;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping(path="/account")
public class RegisterController {

    //AutoWired lets Spring handle the creation of the instance (singleton)
    @Autowired
    private CustomerDAO customerRepo;
    @Autowired
    private UserDAO userRepo;

    @PostMapping(path="/create")
    //consider mapping to UserDTO instead of User
    public @ResponseBody String addUser(@RequestBody UserDTO user, String owner){
        if(Boolean.parseBoolean(owner)) {
            Integer id = this.userRepo.save(user);
            this.customerRepo.save(id);
        } else {
            //this.ownerRepo.save(user);
        }

        return "Successfully saved user";
    }

    @PostMapping(path="/login")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object LoginUser(@RequestBody String username,  String password) {

        User login = this.customerRepo.findByUsername(username);
        if (login == null || !login.getPassword().equals(password)) {
            return "authentication failure";
        }

        return JWT.create().withAudience(username) // ****TO PUT IN A SERVICE FILE**************
                .sign(Algorithm.HMAC256(password));
    }

}
