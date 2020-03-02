package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
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

    @PostMapping(path="/manage")
    public @ResponseBody Object manageUserDetails(@RequestBody UserDTO user) {
        this.userDAO.update(user);
        return "Successfully updated Customer info";
    }

}
