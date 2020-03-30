package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;


import javax.persistence.Embedded;
import java.util.Date;

public class RouteDTO {

    private Integer id;
    private Location location;
    private Date date;


    public RouteDTO(){ };

    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        this.date = r.getDate();
        this.location = r.getLocation();
    }

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
