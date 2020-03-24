package jeaps.foodtruck.common.user.customer.food;

import javax.persistence.Id;

public class FoodDTO {
    @Id
    private String foodtype;

    public String getFoodtype() { return foodtype; }

    public void setFoodtype(String foodtype) { this.foodtype = foodtype; }
}
