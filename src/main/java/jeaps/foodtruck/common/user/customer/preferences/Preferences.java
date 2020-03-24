package jeaps.foodtruck.common.user.customer.preferences;

import jeaps.foodtruck.common.user.customer.food.Food;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Preferences {

    @Id
    private Integer id;

    @ManyToOne
    private Food foodPref;

    private String proxPref;

    private Integer maxPricePref;


}
