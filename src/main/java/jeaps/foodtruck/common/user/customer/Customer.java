package jeaps.foodtruck.common.user.customer;

import jeaps.foodtruck.common.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer extends User {

    //ID from superclass
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
