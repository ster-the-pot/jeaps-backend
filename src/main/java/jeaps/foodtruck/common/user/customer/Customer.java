package jeaps.foodtruck.common.user.customer;


import javax.persistence.Entity;
import javax.persistence.Id;

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
