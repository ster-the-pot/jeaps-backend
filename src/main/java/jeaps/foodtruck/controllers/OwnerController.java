package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import jeaps.foodtruck.common.truck.route.RouteDAO;
import jeaps.foodtruck.common.truck.route.RouteDTO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/owner")
@ResponseBody
public class OwnerController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TruckDAO truckDAO;
    @Autowired
    RouteDAO routeDAO;
    @Autowired
    OwnerDAO ownerDAO;

    @RequestMapping(path="/details", method = RequestMethod.GET)
    public User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }

    @PostMapping(path="/manage")
    public Object manageUserDetails(@RequestBody UserDTO user) {

        this.userDAO.update(user);
        return "Successfully updated Owner info";
    }


    @PostMapping(path="/createTruck")
    public Object createTruck(@RequestBody TruckDTO truck, @RequestParam String username) {
        if(this.ownerDAO.saveTruck(truck, username)) {
            return "Successfully added truck";
        }
        return "Issue adding truck";
    }

    @PostMapping(path="/createRoute")
    public Object createRoute(@RequestBody RouteDTO route, @RequestParam Integer truckID,
                              @RequestParam String username) {

        if(this.routeDAO.saveRoute(route, truckID, username)) {
            return "Successfully added Route";
        }
        return "Issue adding Route";
    }

    @RequestMapping(path="/getRoutes")
    public List<RouteDTO> getRoutes(@RequestParam Integer truckID){
        return this.routeDAO.findByTruck(truckID);
    }



    @RequestMapping(path="/getSubscribers")
    public List<Object> getSubscribers(@RequestParam Integer truckID){
        return this.truckDAO.getSubscribers(truckID);
    }

    @PostMapping(path="/deleteRoute")
    public Object deleteRoute(@RequestParam Integer routeID) {
        this.routeDAO.deleteRoute(routeID);
        return "Successfully deleted route";
    }

    @PostMapping(path="/deleteTruck")
    public Object deleteTruck(@RequestParam Integer truckID) {
        this.truckDAO.delete(truckID);
        return "Successfully deleted truck";
    }

    @RequestMapping(path="/myTrucks",method=RequestMethod.GET)
    public List<Truck> findTruck(@RequestParam String username){
        return this.truckDAO.findByOwner(username);
    }


    @PostMapping(path="/editTruck")
    public Object editTruck(@RequestBody TruckDTO truck, @RequestParam Integer truckID) {
        this.truckDAO.update(truck, truckID);
        return "Successfully updated truck";
    }

   /* @PostMapping(path="/manageRoute")
    public @ResponseBody Object manageRoute(@RequestBody TruckDTO truck) {
        this.truckDAO.update(truck);
        return "Successfully updated Route";
    }*/

}
