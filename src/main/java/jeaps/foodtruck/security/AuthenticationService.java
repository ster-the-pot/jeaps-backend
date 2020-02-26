package jeaps.foodtruck.security;

import jeaps.foodtruck.common.user.User;
import jeaps.foodtruck.common.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UserDAO userDAO;

    //TODO
    //public User authenticateCustomer(){ }
    //TODO
    //public TruckOwner authenticateTruckOwner(){};



}
