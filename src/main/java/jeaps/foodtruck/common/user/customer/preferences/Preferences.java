package jeaps.foodtruck.common.user.customer.preferences;

import jeaps.foodtruck.common.user.customer.food.Food;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;


@Entity
public class Preferences {

    @Id
    private Integer id;

    @ManyToMany
    private Set<Food> foodPref;

    private String proxPref;

    private Integer maxPricePref;


}
