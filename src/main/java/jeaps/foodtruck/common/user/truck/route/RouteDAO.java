package jeaps.foodtruck.common.user.truck.route;

import jeaps.foodtruck.common.user.truck.Truck;
import jeaps.foodtruck.common.user.truck.TruckDTO;
import jeaps.foodtruck.common.user.truck.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDAO {
    @Autowired
    private RouteRepository routeRepo;

    public void save(Route r){ this.routeRepo.save(r); }

    public void save(RouteDTO routeDTO){
        Route r = new Route();
        r.setLocation(routeDTO.getLocation());
        r.setTime(routeDTO.getTime());
        this.routeRepo.save(r);
    }



}