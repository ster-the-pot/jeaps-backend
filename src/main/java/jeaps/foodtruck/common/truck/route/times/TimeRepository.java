package jeaps.foodtruck.common.truck.route.times;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeRepository extends CrudRepository<Time, Integer> {
    List<Time> findByRoute_id(Integer route);
}
