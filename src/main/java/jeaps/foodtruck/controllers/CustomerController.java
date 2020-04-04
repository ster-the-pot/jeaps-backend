package jeaps.foodtruck.controllers;

import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/customer")
@ResponseBody
public class CustomerController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    TruckDAO truckDAO;

    @Autowired
    CustomerDAO customerDAO;

    HashMap<String, Object> map = new HashMap<>();

    @Autowired
    TokenService tokenService;

    @RequestMapping(path="/preferences", method = RequestMethod.GET)
    public List<Object> getPreferences(@RequestParam String username){
        return customerDAO.getPreferences(username);
    }


    @PostMapping(path="/editPreferences")
    public Object editPreferences(@RequestBody Preferences pref, @RequestParam String username) {
        customerDAO.editPreferences(username, pref);
        return "Successfully updated Preferences";
    }


    //WARNING!!!!!!!!!!!!!!!!!! RETURNS PASSWORD
    @RequestMapping(path="/details")
    public User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }


    @PostMapping(path="/manage")
    public Object manageUserDetails(@RequestBody UserDTO user) {
        this.userDAO.update(user);
        return "Successfully updated Customer info";
    }


    @RequestMapping(path="/allTruck")
    public List<Truck> allTruck(){
        return this.truckDAO.findALL();
    }


/*****************************************************************
 * Start Ratings
 *****************************************************************/

    /*****************************************************************
     * End Ratings
     * Start Subscribed
     *****************************************************************/
    @RequestMapping(path="/getSubscribedTrucks")
    public List<Object> getSubscribedTrucks(@RequestParam String username){
        return this.customerDAO.getSubscribedTrucks(username);
    }

    @PostMapping(path="/subscribe")
    public Object subscribeToTruck(@RequestParam String username, @RequestParam Integer truckID) {
        this.customerDAO.subscribeToTruck(username, truckID);
        return "Successfully subscribed to truck";
    }

    @PostMapping(path="/unsubscribe")
    public Object unsubscribeToTruck(@RequestParam String username, @RequestParam Integer truckID) {
        this.customerDAO.unsubscribeToTruck(username, truckID);
        return "Successfully unsubscribed to truck";
    }
    /*****************************************************************
     * End Subscribe
     *****************************************************************/
/*@RequestMapping(path="/searchTruck")
    public @ResponseBody
    List<Truck> searchTruck(@RequestParam String username){
        return this.truckDAO.findALL();
    }*/
}
