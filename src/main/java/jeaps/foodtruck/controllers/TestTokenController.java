package jeaps.foodtruck.controllers;


import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/testToken")

public class TestTokenController {
    @Autowired
    UserDAO userRepo;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Object login( @RequestBody UserDTO user){
        JSONObject jsonObject = new JSONObject();
        User userForBase = userRepo.findByUsername(user.getUsername());
        if(userForBase == null){
            jsonObject.put("message","user is not exist");
            return jsonObject;
        }
        else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","password fail");
                return jsonObject;
            }
            else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "You are current login";
    }
}
