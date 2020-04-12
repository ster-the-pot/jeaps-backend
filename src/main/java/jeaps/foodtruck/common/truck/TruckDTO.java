package jeaps.foodtruck.common.truck;


import jeaps.foodtruck.common.truck.food.Food;

import java.util.List;

public class TruckDTO {
    private Integer id;
    private String name;
    private Prices price;

    private List<Food> food;
    private Double avgRating;

    public TruckDTO(){};

    public TruckDTO(Truck t) {
        this.id = t.getId();
        this.name = t.getName();
        this.price = t.getPrice();
        this.food = t.getFood();
        this.avgRating = t.getAvgRating();
    }


    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
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

    public Prices getPrice() {
        return price;
    }

    public void setPrice(Prices price) {
        this.price = price;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
