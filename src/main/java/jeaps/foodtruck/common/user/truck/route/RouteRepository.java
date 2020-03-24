package jeaps.foodtruck.common.user.truck.route;


import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RouteRepository extends CrudRepository<Route, Integer> {
    List<Route> findByTruck_id(Integer id);
}