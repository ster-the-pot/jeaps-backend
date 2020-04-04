package jeaps.foodtruck.common.truck.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RateDAO {
    @Autowired
    RateRepository rateRepository;



    public Rate findByTruckAndCustomer(Integer truck_id, Integer customer_id) {
        List<Rate> rates = rateRepository.findByTruck_id(truck_id);
        for(Rate r: rates) {
            if (r.getCustomer().getId() == customer_id) {
                return r;
            }
        }
        return null;
    }
}
