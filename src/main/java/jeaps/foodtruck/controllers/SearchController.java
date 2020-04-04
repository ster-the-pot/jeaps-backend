package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/search")
@ResponseBody
public class SearchController {
    @Autowired
    TruckDAO truckDAO;

    @RequestMapping(path="/default")
    public List<Truck> defaultSearch(@RequestParam String truckName) {
        return truckDAO.findByName(truckName);
    }
    @RequestMapping(path="/advanced")
    public List<Truck> advancedSearch(@RequestBody TruckDTO truck) {
        return truckDAO.searchAdvanced(truck);
    }
}
