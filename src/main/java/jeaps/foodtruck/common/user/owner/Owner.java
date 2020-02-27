package jeaps.foodtruck.common.user.owner;


import jeaps.foodtruck.common.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner extends User {

    //ID from superclass
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
