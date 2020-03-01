package jeaps.foodtruck.common.user.owner;


import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.route.Route;
import jeaps.foodtruck.common.user.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Owner extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Set<Truck> trucks;

    public Set<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(Set<Truck> trucks) {
        this.trucks = trucks;
    }
}
