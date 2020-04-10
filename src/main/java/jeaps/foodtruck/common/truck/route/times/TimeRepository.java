package jeaps.foodtruck.common.truck.route.times;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeRepository extends CrudRepository<Time, TimeKey> {
    List<Time> findByRouteId(Integer route);
    Void deleteByRouteId(Integer route);
}
