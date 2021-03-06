package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.times.Time;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private String name;
    @Embedded
    private Location location;

    private Date startTime;
    private Date endTime;

    boolean[] days = new boolean[7];
    //@JsonManagedReference
    //@OneToOne
    //Time days;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
