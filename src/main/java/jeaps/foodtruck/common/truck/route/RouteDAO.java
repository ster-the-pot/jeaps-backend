package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.TruckDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteDAO {
    @Autowired
    private RouteRepository routeRepo;
    @Autowired
    private TruckDAO truckDAO;


    public void setRouteRepo(RouteRepository routeRepo) {
        this.routeRepo = routeRepo;
    }

    public void save(Route r){ this.routeRepo.save(r); }

    public void save(RouteDTO routeDTO){
        Route r = new Route();
        if(routeDTO.getId() != null) {
            r.setId(routeDTO.getId());
        }
        r.setDate(routeDTO.getDate());
        r.setLocation(routeDTO.getLocation());

        this.routeRepo.save(r);
    }

    public void deleteRoute(Integer routeID) {
        routeRepo.deleteById(routeID);
    }


    public List<RouteDTO> findByTruck(Integer truckID) {
        List<Route> routes = this.routeRepo.findByTruck_id(truckID);
        List<RouteDTO> routeDTOList = new ArrayList<>();
        RouteDTO routeDTO;
        for(Route r: routes) {
            routeDTO = new RouteDTO();
            if(r.getId() != null) {
                routeDTO.setId(r.getId());
            }
            routeDTO.setDate(r.getDate());
            //routeDTO.setLocation(r.getLocation());
            routeDTOList.add(routeDTO);
        }
        
        return routeDTOList;
    }

}