package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.truck.route.RouteDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/truck")
public class TruckController {

    @Autowired
    TruckDAO truckDAO;

 /*   @RequestMapping(path="/all")
    public @ResponseBody
    Iterable<Truck> getAllTrucks(){
        return this.truckDAO.getAllTrucks();
    }
*/
    @RequestMapping(path="/details")
    public @ResponseBody
    Truck getUserDetails(@RequestParam String name){
        return this.truckDAO.findByName(name);
    }


    @RequestMapping(path="/search")
    public @ResponseBody
    List<Truck> searchTrucks(@RequestParam String name){
        return null;
    }


    /*One of the requirements is get nearby trucks.
We should make a POST endpoint that recieves coordinates
(lat + long) and an optional distance parameter that returns all trucks in an area*/
    @RequestMapping(path="/getNearbyTrucks")
    public List<Truck> getNearbyTrucks(@RequestBody Location loc, @RequestParam Integer distance){
        return this.truckDAO.getNearbyTrucks(loc, distance);


    }

}
