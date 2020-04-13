package jeaps.foodtruck.common.truck;

import jeaps.foodtruck.common.truck.route.Location;

public class SearchDTO {

    private String name;
    private int rating;
    private int price;
    private String foodType;
    private boolean isOpen;
    private Location location;

    public SearchDTO(String name, int rating, int price, String foodType, boolean isOpen, Location location) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.foodType = foodType;
        this.isOpen = isOpen;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
