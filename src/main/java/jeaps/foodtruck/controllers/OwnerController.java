package jeaps.foodtruck.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDAO;
import jeaps.foodtruck.common.user.truck.TruckDTO;
import jeaps.foodtruck.common.user.truck.route.Route;
import jeaps.foodtruck.common.user.truck.route.RouteDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/owner")
public class OwnerController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TruckDAO truckDAO;
    @Autowired
    RouteDAO routeDAO;
    @Autowired
    OwnerDAO ownerDAO;

    @RequestMapping(path="/details")
    public @ResponseBody
    User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }

    @PostMapping(path="/manage")
    public @ResponseBody Object manageUserDetails(@RequestBody UserDTO user) {

        this.userDAO.update(user);
        return "Successfully updated Owner info";
    }

    @PostMapping(path="/createTruck")
    public @ResponseBody Object createTruck(@RequestBody TruckDTO truck, @RequestParam String username) {
        if(this.ownerDAO.saveTruck(truck, username)) {
            return "Successfully added truck";
        }
        return "Issue adding truck";
    }

    @PostMapping(path="/createRoute")
    public @ResponseBody Object createRoute(@RequestBody Route route, @RequestParam String truckID, @RequestParam String username) {
        if(this.ownerDAO.saveRoute(route, truckID, username)) {
            return "Successfully added Route";
        }
        return "Issue adding Route";
    }

    @PostMapping(path="/deleteTruck")
    public @ResponseBody Object deleteTruck(@RequestBody TruckDTO truck) {
        this.truckDAO.delete(truck);
        return "Successfully delete truck";
    }

    @RequestMapping(path="/findTruck")
    public @ResponseBody
    List<Truck> findTruck(@RequestParam String username){
        return this.truckDAO.findByOwner(username);
    }
    //HOW ARE WE FIGURING OUT if it an owner???????????????????????????????



    @PostMapping(path="/editTruck")
    public @ResponseBody Object editTruck(@RequestBody TruckDTO truck) {
        this.truckDAO.update(truck);
        return "Successfully updated truck";
    }

    @PostMapping(path="/manageRoute")
    public @ResponseBody Object manageRoute(@RequestBody TruckDTO truck) {
        this.truckDAO.update(truck);
        return "Successfully updated truck";
    }

}
