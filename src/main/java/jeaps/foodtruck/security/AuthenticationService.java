package jeaps.foodtruck.security;

import jeaps.foodtruck.common.user.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    CustomerDAO userDAO;

    //TODO
    //public User authenticateCustomer(){ }
    //TODO
    //public TruckOwner authenticateTruckOwner(){};



}
