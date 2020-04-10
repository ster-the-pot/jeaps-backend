package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;
import jeaps.foodtruck.common.truck.route.times.TimeDTO;

import javax.persistence.Embedded;
import java.util.List;


public class RouteDTO {

    private Integer id;
    private Location location;
    private String message;
    private String name;

    private List<TimeDTO> times;
    //Time days;

    public RouteDTO(){ };
    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        this.name = r.getName();

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

    public List<TimeDTO> getTimes() {
        return times;
    }

    public void setTimes(List<TimeDTO> times) {
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
