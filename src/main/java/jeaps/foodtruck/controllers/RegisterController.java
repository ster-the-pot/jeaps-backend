package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.User;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.customer.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping(path="/account")
public class RegisterController {

    //AutoWired lets Spring handle the creation of the instance (singleton)
    @Autowired
    private CustomerDAO userRepo;

    @PostMapping(path="/create")
    //consider mapping to UserDTO instead of User
    public @ResponseBody String addUser(@RequestBody CustomerDTO user){
        this.userRepo.save(user);
        return "Successfully saved user";
    }

    @PostMapping(path="/login")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object LoginUser(@RequestBody CustomerDTO user) {
        System.out.println(user.getPassword());
        User login = this.userRepo.findByUsername(user.getUsername());
        System.out.println(login.getPassword());
        if (login == null || !login.getPassword().equals(user.getPassword())) {
            return "authentication failure";
        }
        return JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

}
