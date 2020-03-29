package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;


import java.util.Date;

public class RouteDTO {

    private Integer id;
    private Location location;
    private Date date;

    private Truck truck;


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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
