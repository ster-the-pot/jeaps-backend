package jeaps.foodtruck.common.truck.route.times;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.Day;
import jeaps.foodtruck.common.truck.route.Route;

import javax.persistence.*;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    Day day;
    String times;

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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
