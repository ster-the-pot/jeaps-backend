package jeaps.foodtruck.common.user.truck.route;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Route {

    @Id
    private Integer id;
    private Location location;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
