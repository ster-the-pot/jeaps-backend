package jeaps.foodtruck.common.truck.route;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.route.times.Time;
import jeaps.foodtruck.common.truck.route.times.TimeDAO;
import jeaps.foodtruck.common.truck.route.times.TimeDTO;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RouteDAO {
    @Autowired
    private RouteRepository routeRepo;
    @Autowired
    private TruckDAO truckDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OwnerDAO ownerDAO;
    @Autowired
    private TimeDAO timeDAO;



    public void setRouteRepo(RouteRepository routeRepo) {
        this.routeRepo = routeRepo;
    }

    public void save(Route r){ this.routeRepo.save(r); }

    public Optional<Route> findByID(Integer id) {  return this.routeRepo.findById(id); }




    public Route saveRoute(RouteDTO routeDTO, Integer truckID, String username){

        User user = this.userDAO.findByUsername(username);
        Optional<Owner> owner = ownerDAO.findById(user.getId());
        Optional<Truck> t =  this.truckDAO.findById(truckID);
        List<Truck> trucks = owner.get().getTrucks();
        if(!owner.isPresent() || !t.isPresent() || !trucks.contains(t.get())) {
            throw new RuntimeException("Truck or Owner given is invalid");
        }

        trucks.remove(t.get());

        Route route = new Route();

        route.setStartTime(routeDTO.getStartTime());
        route.setEndTime(routeDTO.getEndTime());
        route.setDays(routeDTO.getDays());
        route.setName(routeDTO.getName());
        route.setMessage(routeDTO.getMessage());
        route.setLocation(routeDTO.getLocation());
        routeRepo.save(route);

        List<Route> r = t.get().getRoute();
        r.add(route);
        t.get().setRoute(r);

        trucks.add(t.get());

        owner.get().setTrucks(trucks);
        ownerDAO.save(owner.get());

        return route;
    }

    public Route editRoute(RouteDTO routeDTO){
        Optional<Route> old = this.findByID(routeDTO.getId());
        if(old.isPresent()) {
            Truck t =  old.get().getTruck();

            Owner owner = t.getOwner();

            List<Truck> trucks = owner.getTrucks();

            trucks.remove(t);

            Optional<Route> route = this.routeRepo.findById(routeDTO.getId());
            if(route.isPresent()) {
                List<Route> r = t.getRoute();
                r.remove(route.get());

                route.get().setStartTime(routeDTO.getStartTime());
                route.get().setEndTime(routeDTO.getEndTime());
                route.get().setDays(routeDTO.getDays());
                route.get().setName(routeDTO.getName());
                route.get().setLocation(routeDTO.getLocation());
                route.get().setMessage(routeDTO.getMessage());

                r.add(route.get());
                t.setRoute(r);

                trucks.add(t);

                owner.setTrucks(trucks);
                ownerDAO.save(owner);

                return route.get();
            }
        } else {
            throw new RuntimeException("Route does not exist");
        }
        return null;
    }


    public void deleteRoute(Integer routeID) {

        Optional<Route> route = routeRepo.findById(routeID);

        if(!route.isPresent()) {
            throw new RuntimeException("Route doesn't exist");
        }
        Owner owner = route.get().getTruck().getOwner();
        Truck t =  route.get().getTruck();

        List<Truck> trucks = owner.getTrucks();
        trucks.remove(t);

        List<Route> r = t.getRoute();
        r.remove(route.get());


        t.setRoute(r);

        trucks.add(t);

        owner.setTrucks(trucks);
        ownerDAO.save(owner);

        deleteTimes(route.get());
        this.routeRepo.deleteById(routeID);
    }

    public void deleteAllRoute(List<Integer> routeIDs) {
        for (Integer routeID : routeIDs) {
            this.deleteRoute(routeID);
        }
    }

    public List<RouteDTO> findByTruck(Integer truckID) {
        List<Route> routes = this.routeRepo.findByTruck_id(truckID);
        List<RouteDTO> routeDTOList = new ArrayList<>();

        for(Route r: routes) {
            routeDTOList.add(new RouteDTO(r));
        }
        return routeDTOList;
    }



    private void deleteTimes(Route r) {


    }
}