package jeaps.foodtruck.controllers;

import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.rate.RateDAO;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/rate")
@ResponseBody
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingsController {
    @Autowired
    RateDAO rateDAO;
    @Autowired
    TokenService tokenService;

    Map<String, Object> map = new HashMap<>();

    
    @UserLoginToken
    @PostMapping(path="/customer/addRating")
    public ResponseEntity<?> addRating(@RequestBody RateDTO rate, @RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.addRate(rate, truckid, tokenService.getUsername(req));
        //rateDAO.addRate(rate, truckid, username);
        return ResponseEntity.ok("Rating successfully added");
    }

    @UserLoginToken
    @PostMapping(path="/customer/editRating")
    public ResponseEntity<?> editRating(@RequestBody RateDTO rate, @RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.editRate(rate, truckid, tokenService.getUsername(req));
        //rateDAO.editRate(rate, truckid, username);
        return ResponseEntity.ok("Rating successfully edited");
    }

    @UserLoginToken
    @PostMapping(path="/customer/deleteRating")
    public ResponseEntity<?> editRating(@RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.removeRate(truckid, tokenService.getUsername(req));
        //rateDAO.removeRate(truckid, username);
        return ResponseEntity.ok("Rating successfully deleted");
    }

    @RequestMapping(path="/getAverageRating", method = RequestMethod.GET)
    public ResponseEntity<?> getAverageRating(@RequestParam Integer truckid){
        map.clear();
        Double avg = rateDAO.getAvgRating(truckid);

        map.put("Ratings", avg );

        return ResponseEntity.created(URI.create("/getAverageRating/done")).body(map);
    }

    @RequestMapping(path="/getFullTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getFullTruckRatings(@RequestParam Integer truckid) {
        List<Rate> rate = rateDAO.getRatingsObject(truckid);

        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getFullTruckRatings/done")).body(map);
    }

    @RequestMapping(path="/getTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getTruckRatings(@RequestParam Integer truckid){

        List<Integer> rate = rateDAO.getRatings(truckid);
        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getTruckRatings/done")).body(map);
    }
}
