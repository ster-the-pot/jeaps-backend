package jeaps.foodtruck.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jeaps.foodtruck.common.user.truck.TruckDAO;
import jeaps.foodtruck.common.user.truck.TruckDTO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/owner")
public class OwnerController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TruckDAO truckDAO;


    @RequestMapping(path="/details")
    public @ResponseBody
    User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }

    @PostMapping(path="/manage")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object manageUserDetails(@RequestBody UserDTO user) {

        this.userDAO.update(user);
        return "Successfully updated Owner info";
    }

    @PostMapping(path="/createTruck")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object createTruck(@RequestBody TruckDTO truck) {
        this.truckDAO.save(truck);
        return "Successfully added truck";
    }
    @PostMapping(path="/manageTruck")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object manageTruck(@RequestBody TruckDTO truck) {
        this.truckDAO.update(truck);
        return "Successfully updated truck";
    }
}
