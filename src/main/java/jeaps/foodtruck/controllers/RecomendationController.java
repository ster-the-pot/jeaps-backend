package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class RecomendationController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(path="/customer/recomendation")
    public List<Truck> getRecomendation(@RequestParam String username){
        return customerDAO.getRecomendations(username);
    }

    @RequestMapping(path="/recomendation")
    public Object getRecomendation(@RequestBody UserDTO user) {
       return null;
    }

}
