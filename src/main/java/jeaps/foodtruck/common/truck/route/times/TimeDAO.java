package jeaps.foodtruck.common.truck.route.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TimeDAO {
    @Autowired
    TimeRepository timeRepository;


    public Optional<Time> findByID(int id) {
        return timeRepository.findById(id);
    }

    public List<Time> findByRoute(int routeID) {
        return timeRepository.findByRoute_id(routeID);
    }

    public void save(Time time) {
        this.timeRepository.save(time);
    }

    public void delete(int id) {
        this.timeRepository.deleteById(id);
    }
}
