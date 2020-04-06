package jeaps.foodtruck.controllers;



import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.route.Location;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
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
    @Autowired
    OwnerDAO ownerDAO;

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
    public ResponseEntity<?> getNearbyTrucks(@RequestBody Location loc, @RequestParam Integer distance) throws URISyntaxException {
        List<Truck> trucks = this.truckDAO.getNearbyTrucks(loc, distance);


        map.clear();
        map.put("TruckList", trucks);
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(map);
    }



    @RequestMapping(path="/ownerStatsTruckId")
    public ResponseEntity<?> ownerStats(@RequestParam Integer truckId) {
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(this.ownerDAO.getOwnerStats(truckId));

    }


    @RequestMapping(path="/ownerStatsUsername")
    public ResponseEntity<?> ownerStats(@RequestParam String username){
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(this.ownerDAO.getOwnerStats(username));
    }

}
