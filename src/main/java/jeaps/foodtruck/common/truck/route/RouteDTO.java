package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;


import javax.persistence.Embedded;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RouteDTO {

    private Integer id;
    private Location location;
    private String message;
    //Date startTime;
    //Date endTime;
    private String name;
   // priprivate vate List<Time> days;

    private Time Sunday;

    private Time Monday;

    private Time Tuesday;

    private Time Wednesday;

    private Time Thursday;

    private Time Friday;

    private Time Saturday;

    public RouteDTO(){ };
    public RouteDTO(Route r){
        if(r.getId() != null) {
            this.id = r.getId();
        }
        this.name = r.getName();

        this.Sunday = r.getSunday();
        this.Monday = r.getMonday();
        this.Tuesday = r.getTuesday();
        this.Wednesday = r.getWednesday();
        this.Thursday = r.getThursday();
        this.Friday = r.getFriday();
        this.Saturday = r.getSaturday();
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

    public Time getSunday() {
        return Sunday;
    }

    public void setSunday(Time sunday) {
        Sunday = sunday;
    }

    public Time getMonday() {
        return Monday;
    }

    public void setMonday(Time monday) {
        Monday = monday;
    }

    public Time getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Time tuesday) {
        Tuesday = tuesday;
    }

    public Time getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Time wednesday) {
        Wednesday = wednesday;
    }

    public Time getThursday() {
        return Thursday;
    }

    public void setThursday(Time thursday) {
        Thursday = thursday;
    }

    public Time getFriday() {
        return Friday;
    }

    public void setFriday(Time friday) {
        Friday = friday;
    }

    public Time getSaturday() {
        return Saturday;
    }

    public void setSaturday(Time saturday) {
        Saturday = saturday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
