package jeaps.foodtruck.common.user.customer;

import javax.persistence.Id;

public class CustomerDTO {

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
