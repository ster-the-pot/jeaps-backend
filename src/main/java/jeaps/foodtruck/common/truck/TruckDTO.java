package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.route.RouteDTO;

import java.util.List;

public class TruckDTO {
    private Integer id;
    private String name;
    private String menu;
    private Prices price;

    private List<Food> food;
    private List<RouteDTO> routes;

    public TruckDTO(){};
    public TruckDTO(Truck t) {
        id = t.getId();
        name = t.getName();
        menu = t.getMenu();
        price = t.getPrice();
        food = t.getFood();
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

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDTO> routes) {
        this.routes = routes;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
