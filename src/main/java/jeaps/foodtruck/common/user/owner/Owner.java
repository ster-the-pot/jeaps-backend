package jeaps.foodtruck.common.user.owner;


import jeaps.foodtruck.common.user.User.User;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner extends User {

    //ID from superclass
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
