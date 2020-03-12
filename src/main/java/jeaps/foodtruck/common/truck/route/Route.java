package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Location location;
    private Date date;

    @ManyToOne Truck truck;


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
