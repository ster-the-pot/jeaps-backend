package jeaps.foodtruck.common.user.truck.route;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Route {
    @Id
    private Integer id;

    private List<Location> locations;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
