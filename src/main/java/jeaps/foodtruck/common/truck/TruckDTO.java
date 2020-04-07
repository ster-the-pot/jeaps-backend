package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.food.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class TruckDTO {
    private Integer id;
    private String name;
    private String menu;
    private Prices price;
    private List<Food> food;

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

    /*public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }*/

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
