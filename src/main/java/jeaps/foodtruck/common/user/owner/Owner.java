package jeaps.foodtruck.common.user.owner;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner {

    //ID from superclass
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
