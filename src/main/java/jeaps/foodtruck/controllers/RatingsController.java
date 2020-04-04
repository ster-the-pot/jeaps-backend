package jeaps.foodtruck.controllers;

import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/rate")
@ResponseBody
public class RatingsController {
    @Autowired
    TruckDAO truckDAO;
    @Autowired
    TokenService tokenService;

    Map<String, Object> map = new HashMap<>();


    @UserLoginToken
    @PostMapping(path="/customer/addRating")
    public ResponseEntity<?> addRating(@RequestBody RateDTO rate, @RequestParam Integer truck_id, HttpServletRequest req) {
        truckDAO.addRate(rate, truck_id, tokenService.getUsername(req));
        return ResponseEntity.ok("Rating successfully added");
    }

    @UserLoginToken
    @PostMapping(path="/customer/editRating")
    public ResponseEntity<?> editRating(@RequestBody RateDTO rate, @RequestParam Integer truck_id, HttpServletRequest req) {
        truckDAO.editRate(rate, truck_id, tokenService.getUsername(req));
        return ResponseEntity.ok("Rating successfully edited");
    }

    @UserLoginToken
    @PostMapping(path="/customer/deleteRating")
    public ResponseEntity<?> editRating(@RequestBody Integer truck_id, HttpServletRequest req) {
        truckDAO.removeRate(truck_id, tokenService.getUsername(req));
        return ResponseEntity.ok("Rating successfully deleted");
    }

    @RequestMapping(path="/getAverageRating", method = RequestMethod.GET)
    public ResponseEntity<?> getAverageRating(@RequestParam Integer truck_id){
        return ResponseEntity.ok(truckDAO.getAvgRating(truck_id));
    }

    @RequestMapping(path="/getFullTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getFullTruckRatings(@RequestParam Integer truck_id) {
        List<Rate> rate = truckDAO.getRatingsObject(truck_id);

        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getFullTruckRatings/done")).body(map);
    }

    @RequestMapping(path="/getTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getTruckRatings(@RequestParam Integer truck_id){

        List<Integer> rate = truckDAO.getRatings(truck_id);
        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getTruckRatings/done")).body(map);
    }
}
