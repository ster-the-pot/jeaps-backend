package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;


import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDTO {

    private Integer id;
    private Location location;
    private String message;
    Date startTime;
    Date endTime;
    private String name;

    public RouteDTO(){ };

    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        this.name = r.getName();
        this.startTime = r.getStartTime();
        this.endTime = r.getEndTime();
        this.location = r.getLocation();
        this.message = r.getMessage();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
