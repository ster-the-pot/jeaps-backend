package jeaps.foodtruck.common.truck.route;

import java.util.Date;

public class RouteDTO {

    //How will this be represented?
    private Location location;
    private Date date;


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
