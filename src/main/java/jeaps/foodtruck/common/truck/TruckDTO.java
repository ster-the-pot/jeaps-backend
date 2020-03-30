package jeaps.foodtruck.common.truck;



public class TruckDTO {
    private Integer id;
    private String name;
    private String menu;
    private Prices price;
    private FoodTypes type;



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
}
