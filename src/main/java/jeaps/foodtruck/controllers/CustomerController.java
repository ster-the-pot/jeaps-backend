package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.User.User;
import jeaps.foodtruck.common.user.User.UserDAO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    UserDAO userDAO;


    @RequestMapping(path="/details")
    public @ResponseBody
    User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }


}
