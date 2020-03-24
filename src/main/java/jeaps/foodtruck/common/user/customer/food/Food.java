package jeaps.foodtruck.common.user.customer.food;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    private String foodtype;

    public String getFoodtype() { return foodtype; }

    public void setFoodtype(String foodtype) { this.foodtype = foodtype; }
}
