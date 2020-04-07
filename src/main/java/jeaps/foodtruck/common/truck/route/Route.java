package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;


import javax.persistence.*;
import java.util.*;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private String name;
    @Embedded
    private Location location;



    @JsonManagedReference
    @OneToOne
    private Time Sunday;

    @JsonManagedReference
    @OneToOne
    private Time Monday;

    @JsonManagedReference
    @OneToOne
    private Time Tuesday;

    @JsonManagedReference
    @OneToOne
    private Time Wednesday;

    @JsonManagedReference
    @OneToOne
    private Time Thursday;

    @JsonManagedReference
    @OneToOne
    private Time Friday;

    @JsonManagedReference
    @OneToOne
    private Time Saturday;

    //@Enumerated(EnumType.ORDINAL)
    //@OneToMany(cascade = CascadeType.ALL)
    //@MapKey
    //private Map<Day, Time> days = new HashMap<>();
    //private List<Time> days= new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Truck truck;


    public Route() {

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


    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
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
