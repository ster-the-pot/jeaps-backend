package jeaps.foodtruck.common.truck;

import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.Prices;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    //SHOULD BE AN IMAGE??
    private String menu;
    private Prices price;

    @ManyToOne Owner owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Set<Customer> customers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Set<Route> route;

    public Truck(){};

    public Truck(String name, Set<Route> route, String type, String menu){
        this.setName(name);
        this.setRoute(route);
        this.setType(type);
        this.setMenu(menu);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPrice(Prices price){this.price = price;}

    public Prices getPrice(){return this.price;}
}
