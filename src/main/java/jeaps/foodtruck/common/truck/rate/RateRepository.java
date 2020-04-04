package jeaps.foodtruck.common.truck.rate;


import jeaps.foodtruck.common.truck.Truck;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Integer> {
    List<Rate> findByTruck_id(Integer id);
}
