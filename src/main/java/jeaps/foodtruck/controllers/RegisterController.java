package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.User;
import jeaps.foodtruck.common.user.UserDAO;
import jeaps.foodtruck.common.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegisterController {

    //AutoWired lets Spring handle the creation of the instance (singleton)
    @Autowired
    private UserDAO userRepo;

    @PostMapping(path="/register")
    //consider mapping to UserDTO instead of User
    public @ResponseBody String addUser(UserDTO user){
        this.userRepo.save(user);
        return "Successfully saved user";
    }

    @PostMapping(path="/login")
    //consider mapping to UserDTO instead of User
    public @ResponseBody Object LoginUser(UserDTO user) {

        User login = this.userRepo.findByUsername(user.getUsername());
        if (login == null || login.getPassword() != user.getPassword()) {
            return "authentication failure";
        }
        return login;

    }

}
