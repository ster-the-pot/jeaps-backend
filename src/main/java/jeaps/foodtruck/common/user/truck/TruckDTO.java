package jeaps.foodtruck.common.user.truck;

import jeaps.foodtruck.common.user.truck.route.Route;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Set;

public class TruckDTO {

    private Integer owner_id;
    private String name;
    private String type;
    //SHOULD BE AN IMAGE??
    private String menu;
    private Set<Route> route;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Set<Route> getRoute() {
        return route;
    }

    public void setRoute(Set<Route> route) {
        this.route = route;
    }
}
