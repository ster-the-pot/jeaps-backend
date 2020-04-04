package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.route.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    Optional<Truck> getUserDetails(@RequestParam Integer truckID){
        return this.truckDAO.findById(truckID);
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
