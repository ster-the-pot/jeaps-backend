package jeaps.foodtruck.controllers;


import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.net.URI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/account")
@ResponseBody
public class RegisterController {

    //AutoWired lets Spring handle the creation of the instance (singleton)
    @Autowired
    private CustomerDAO customerRepo;
    @Autowired
    private OwnerDAO ownerRepo;
    @Autowired
    private UserDAO userRepo;
    @Autowired
    TokenService tokenService;

    /**
     * Hashes the password of a user
     * @param user the user to hash the password of
     * @return a UserDTO with a hashed password
     */
    private UserDTO hashPass(UserDTO user){

        //Hash the password
        MessageDigest md = null;
        String credHash = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(user.getPassword().getBytes());
            byte[] digest = md.digest();
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, digest);
            // Convert message digest into hex value
            credHash = no.toString(16).toUpperCase();

            //Set the hashed password before saving
            user.setPassword(credHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("The MD5 algorithm has ceased to exist");
        }

        return user;
    }

    @PostMapping(path="/register/customer")
    public String registerCustomer(@RequestBody UserDTO user) {

        Integer id = this.userRepo.save(hashPass(user));
        this.customerRepo.save(id);
        return "Successfully saved Customer";
    }

    @PostMapping(path="/register/owner")
    public String registerOwner(@RequestBody UserDTO user){
        Integer id = this.userRepo.save(hashPass(user));
        this.ownerRepo.save(id);
        return "Successfully saved Owner";
    }


    @PostMapping(path="/login")
    public ResponseEntity<?> LoginUser(@RequestBody UserDTO user) {

        user = hashPass(user);

        if(!this.userRepo.existsByUsername(user.getUsername())){
            return ResponseEntity.status(403).body("Invalid Credentials");
        }

        User login = this.userRepo.findByUsername(user.getUsername());

        if (login == null || login.getId() == null || !login.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(403).body("Invalid Credentials");
        }

        Optional<Owner> owner = this.ownerRepo.findById(login.getId());
        String type;
        if(!owner.isPresent()) {
            type = "customer";
        } else {
            type = "owner";
        }

        String token = tokenService.getToken(login, type);

        HashMap<String,String> str = new HashMap<>();
        //str.put("token",JWT.create().withAudience(user.getUsername()).sign(Algorithm.HMAC256(user.getPassword())));
        str.put("token", token);
        str.put("type",type);
        return ResponseEntity.created(URI.create("/login/done")).body(str);
    }

}
