package jeaps.foodtruck.common.user.customer;


import jeaps.foodtruck.common.user.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//Inherits from User, @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// this strategy makes it so that each class is placed into it's own table (ID's will still be unique
@Entity
public class Customer {

    //ID from superclass
    @Id
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
