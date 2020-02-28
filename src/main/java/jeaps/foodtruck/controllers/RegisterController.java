package jeaps.foodtruck.controllers;


import jeaps.foodtruck.common.user.User.User;
import jeaps.foodtruck.common.user.User.UserDAO;
import jeaps.foodtruck.common.user.User.UserDTO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
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
    private OwnerDAO ownerRepo;
    @Autowired
    private UserDAO userRepo;


    @PostMapping(path="/create")

    //consider mapping to UserDTO instead of User
    public @ResponseBody String addUser(@RequestBody UserDTO user, @RequestParam String owner) {

        Integer id = this.userRepo.save(user);
        if(Boolean.parseBoolean(owner)) {
            this.ownerRepo.save(id);
        } else {
            this.customerRepo.save(id);
        }

        return "Successfully saved user";
    }

    @PostMapping(path="/login")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object LoginUser(@RequestBody UserDTO user) {

        User login = this.userRepo.findByUsername(user.getUsername());
        if (login == null || !login.getPassword().equals(user.getPassword())) {
            return "authentication failure";
        }

        //FIGURE OUT IF CUSTOMER OR OWNER

        return JWT.create().withAudience(user.getUsername()) // ****TO PUT IN A SERVICE FILE**************
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

}
