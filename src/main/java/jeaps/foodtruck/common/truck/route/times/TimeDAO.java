package jeaps.foodtruck.common.truck.route.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TimeDAO {
    @Autowired
    TimeRepository timeRepo;


    public Optional<Time> findByID(TimeKey key) {
        return timeRepo.findById(key);
    }

    public List<Time> findByRoute(int routeID) {
        return timeRepo.findByRouteId(routeID);
    }

    public List<TimeDTO> findDTOByRoute(int routeID) {
        List<Time> times = timeRepo.findByRouteId(routeID);
        List<TimeDTO> returns = new ArrayList<>();

        for(Time t: times) {
            returns.add(new TimeDTO(t));
        }
        return returns;
    }

    public void save(Time time) {
        this.timeRepo.save(time);
    }

    public void delete(int routeId) {
        this.timeRepo.deleteByRouteId(routeId);
    }

    public void saveAllForRoute(List<TimeDTO> times, Integer routeId) {

        Time t = new Time();
        t.setRouteId(routeId);
        for(TimeDTO dto: times) {
            t.setStartTime(dto.getStartTime());
            t.setEndTime(dto.getEndTime());
            t.setDay(dto.getDay());
            this.timeRepo.save(t);
        }
    }




}
