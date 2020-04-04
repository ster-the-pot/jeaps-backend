package jeaps.foodtruck.controllers;



import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.route.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path="/truck")
public class TruckController {

    Map<String, Object> map = new HashMap<String, Object>();

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
    //FIXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    @RequestMapping(path="/getNearbyTrucks")
    public ResponseEntity<?> getNearbyTrucks(@RequestBody Location loc, @RequestParam Integer distance) throws URISyntaxException {
        //return this.truckDAO.getNearbyTrucks(loc, distance);
        if(distance == null) {
            distance = 100000;
            System.out.println("Distance was null");
        }
        if(loc.getLongitude() == null) {
            loc.setLongitude(100000.0);
            System.out.println("Longitude was null");
        }
        if(loc.getLatitude() == null) {
            loc.setLatitude(100000.0);
            System.out.println("Latitude was null");
        }
        //Some of the current routes are null -> issues
        //List<Truck> trucks = this.truckDAO.getNearbyTrucks(loc, distance);
        List<Truck> trucks = (List<Truck>) this.truckDAO.getAllTrucks();


        map.clear();
        map.put("TruckList", trucks);
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(map);
    }

}
