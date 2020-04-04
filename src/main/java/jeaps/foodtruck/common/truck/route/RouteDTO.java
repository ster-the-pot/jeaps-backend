package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;
import jeaps.foodtruck.common.truck.route.times.TimeDTO;


import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDTO {

    private Integer id;
    private Location location;
    private String message;
    private List<TimeDTO> days;

    public RouteDTO(){ };

    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        List<TimeDTO> convert = new ArrayList<>();
        for(Time t: r.getDays()) {
            convert.add(new TimeDTO(t));
        }
        this.days = convert;
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

    public List<TimeDTO> getDays() {
        return days;
    }

    public void setDays(List<TimeDTO> days) {
        this.days = days;
    }
}
