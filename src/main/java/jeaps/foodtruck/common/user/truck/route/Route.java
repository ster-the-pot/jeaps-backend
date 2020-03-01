package jeaps.foodtruck.common.user.truck.route;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Route {
    @Id
    private Integer id;

    //Set<Pair<Date,Location>> route;

//    public Set<Pair<Date, Location>> getRoute() {
//        return route;
//    }
//
//    public void setRoute(Set<Pair<Date, Location>> route) {
//        this.route = route;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
