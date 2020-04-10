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


}
