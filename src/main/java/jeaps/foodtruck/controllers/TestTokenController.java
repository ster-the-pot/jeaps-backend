package jeaps.foodtruck.controllers;


import com.auth0.jwt.JWT;
import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/testToken")
public class TestTokenController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Object login( @RequestBody UserDTO user){
        JSONObject jsonObject = new JSONObject();
        User userForBase = userDAO.findByUsername(user.getUsername());
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
                String token = tokenService.getToken(userForBase, "customer");
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @CrossOrigin
    @UserLoginToken
    @GetMapping("/getMessage/customer")
    public String getMessage(HttpServletRequest req){
        /*System.out.println(req.getRequestURL().toString());
        if(req.getRequestURL().toString().contains("customer")) {
            System.out.println("TRUE");
        }*/
        String token = req.getHeader("token");
        System.out.println(JWT.decode(token).getAudience().get(0));
        System.out.println(JWT.decode(token).getAudience().get(1));
        return tokenService.getUsername(req);
    }
}
