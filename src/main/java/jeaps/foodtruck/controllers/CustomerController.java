package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
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
    public @ResponseBody Object manageUserDetails(@RequestBody UserDTO user) {
        this.userDAO.update(user);
        return "Successfully updated Customer info";
    }

    @RequestMapping(path="/allTruck")
    public @ResponseBody
    List<Truck> allTruck(){
        return this.truckDAO.findALL();
    }

/*@RequestMapping(path="/searchTruck")
    public @ResponseBody
    List<Truck> searchTruck(@RequestParam String username){
        return this.truckDAO.findALL();
    }*/
}
