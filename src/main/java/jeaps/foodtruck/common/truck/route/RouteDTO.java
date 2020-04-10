package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;

import java.util.Date;


public class RouteDTO {

    private Integer id;
    private Location location;
    private String message;
    private String name;

    private Date startTime;
    private Date endTime;

    boolean[] days = new boolean[7];

    //Time days;

    public RouteDTO(){ };
    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        this.name = r.getName();
        this.endTime = r.getEndTime();
        this.startTime = r.getStartTime();
        this.setDays(r.getDays());
        this.location = r.getLocation();
        this.message = r.getMessage();
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

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        System.arraycopy(days, 0, this.days, 0, 7);

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

   /* public Time getDays() {
        return days;
    }

    public void setDays(Time days) {
        this.days = days;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
