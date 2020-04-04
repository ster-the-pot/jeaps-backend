package jeaps.foodtruck.common.truck.route.times;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.Day;
import jeaps.foodtruck.common.truck.route.Route;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    Day day;
    Date startTime;
    Date endTime;

    @JsonBackReference
    @ManyToOne
    private Route route;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
