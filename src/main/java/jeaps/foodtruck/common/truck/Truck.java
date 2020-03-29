package jeaps.foodtruck.common.truck;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.Prices;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //SHOULD BE AN IMAGE??
    private String menu;
    private Prices price;
    private FoodTypes type;

    @JsonBackReference
    @ManyToOne Owner owner;


    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "trucks")
   /* @JoinTable(name="subscribed",
            joinColumns=@JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="customer_id"))*/
    private List<Customer> customers = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Set<Route> route;

    public Truck(){};

    public Truck(String name, Set<Route> route, String type, String menu){
        this.setName(name);
        this.setRoute(route);
        this.setType(FoodTypes.valueOf(type));
        this.setMenu(menu);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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

    public void setType(FoodTypes type){this.type = type;}

    public FoodTypes getType(){return this.type;}
}
