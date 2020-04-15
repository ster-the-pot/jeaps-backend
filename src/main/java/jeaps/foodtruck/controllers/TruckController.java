package jeaps.foodtruck.controllers;



import jeaps.foodtruck.common.truck.SearchDTO;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
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

    @RequestMapping(path="/all")
    public @ResponseBody
    Iterable<Truck> getAllTrucks(){
        return this.truckDAO.getAllTrucks();
    }

    @RequestMapping(path="/details")
    public @ResponseBody
    Optional<Truck> getUserDetails(@RequestParam Integer truckID){
        return this.truckDAO.findById(truckID);
    }

    @RequestMapping(path="/ownerStatsTruckId")
    public ResponseEntity<?> ownerStats(@RequestParam Integer truckId) {
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(this.ownerDAO.getOwnerStats(truckId));

    }


    @RequestMapping(path="/ownerStatsUsername")
    public ResponseEntity<?> ownerStats(@RequestParam String username){
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(this.ownerDAO.getOwnerStats(username));
    }


    @PostMapping(path="/getNearbyTrucks")
    public ResponseEntity<?> getNearbyTrucks(@RequestBody Location loc, @RequestParam Integer distance) throws URISyntaxException {
        List<Truck> trucks = this.truckDAO.getNearbyTrucks(loc, distance);

        map.clear();
        map.put("TruckList", trucks);
        return ResponseEntity.created(URI.create("/getNearbyTrucks/done")).body(map);
    }

    /**
     * Searches for a truck based on its name
     * @param truckName The name of the truck to search for
     * @return A List of trucks with the given name
     */
    @RequestMapping(path="/default")
    public List<Truck> defaultSearch(@RequestParam String truckName) {
        return truckDAO.findByName(truckName);
    }

    /**
     * Searches for a truck based on user-given parameters
     * @param truck A TruckDTO that has one or more attributes of a truck you wish to find
     * @return A list of trucks that conform to the user-given parameters
     */
    @RequestMapping(path="/advanced")
    public List<Truck> advancedSearch(@RequestBody TruckDTO truck) {
        return truckDAO.searchAdvanced(truck);
    }

    /**
     * Searches for a truck based on user-given parameters
     * @param searchParam A SearchDTO that has one or more attributes of desired truck
     * @return A list of trucks that conform to the user-given parameters
     */
    @RequestMapping(path="/advanced/search")
    public List<Truck> superAdvancedSearch(@RequestBody SearchDTO searchParam) {
        return truckDAO.superAdvancedSearch(searchParam);
    }
}
