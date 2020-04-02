package jeaps.foodtruck.common.truck;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.truck.route.Route;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //SHOULD BE AN IMAGE??
    private String menu;
    @Enumerated(EnumType.ORDINAL)
    private Prices price;
    @Enumerated(EnumType.ORDINAL)
    private FoodTypes type;

    @JsonBackReference
    @ManyToOne
    private Owner owner;


    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "trucks")
    private List<Customer> customers = new ArrayList<>();



    @JsonManagedReference
    //@OneToMany(mappedBy = "truck", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private List<Route> route = new ArrayList<>();


    public Truck(){};

    public Truck(String name, String type, String menu){
        this.setName(name);
        this.setType(FoodTypes.valueOf(type));
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Prices getPrice() {
        return price;
    }

    public void setPrice(Prices price) {
        this.price = price;
    }

    public FoodTypes getType() {
        return type;
    }

    public void setType(FoodTypes type) {
        this.type = type;
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

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }




}
