package jeaps.foodtruck.controllers;


import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/account")
@ResponseBody
public class RegisterController {

    //AutoWired lets Spring handle the creation of the instance (singleton)
    @Autowired
    private CustomerDAO customerRepo;
    @Autowired
    private OwnerDAO ownerRepo;
    @Autowired
    private UserDAO userRepo;


    @PostMapping(path="/register/customer")
    public String registerCustomer(@RequestBody UserDTO user) {
        Integer id = this.userRepo.save(user);
        this.customerRepo.save(id);
        return "Successfully saved Customer";
    }

    @PostMapping(path="/register/owner")
    public String registerOwner(@RequestBody UserDTO user){
        Integer id = this.userRepo.save(user);
        this.ownerRepo.save(id);
        return "Successfully saved Owner";
    }

    @PostMapping(path="/login")
    public Object LoginUser(@RequestBody UserDTO user) {

        User login = this.userRepo.findByUsername(user.getUsername());
        if (login == null || login.getId() == null || !login.getPassword().equals(user.getPassword())) {
            return "authentication failure";
        }

        Optional<Owner> owner = this.ownerRepo.findById(login.getId());
        String type;
        if(!owner.isPresent()) {
            type = "Customer";
        } else {
            type = "Owner";
        }


        //TODO Authentication

        // ****TO PUT IN A SERVICE FILE**************
        List<String> str = new ArrayList<String>();
        str.add(JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword())));
        str.add(type);
        return str;
    }

}
