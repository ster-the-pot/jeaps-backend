package jeaps.foodtruck.common.truck;

import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.user.customer.food.Food;

import java.util.Set;

public class TruckDTO {

    private Integer owner_id;
    private String name;
    //SHOULD BE AN IMAGE??
    private String menu;
    private Prices price;
    private Set<Route> route;
    private FoodTypes type;

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
