package jeaps.foodtruck.common.user.owner;

import javax.persistence.Id;

public class OwnerDTO {

    @Id
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
