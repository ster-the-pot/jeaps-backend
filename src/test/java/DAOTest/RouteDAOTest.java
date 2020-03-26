package DAOTest;

import jeaps.foodtruck.common.truck.FoodTypes;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.route.RouteDAO;
import jeaps.foodtruck.common.truck.route.RouteRepository;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteDAOTest {

    @Autowired
    private RouteDAO routeDAO = new RouteDAO();

    RouteRepository routeRepo;

    User userTest = new User();
    Optional<Owner> ownerTest = Optional.of(new Owner());
    Truck truckTest2 = new Truck();
    Route routeTest = new Route();
    Set<Route> SetrouteTest;

    @Before
    public void setUp() {
        routeRepo = mock(RouteRepository.class);
        routeDAO.setRouteRepo(routeRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");

        ownerTest.get().setId(userTest.getId());


        routeTest.setDate(new Date("2020-12-12"));

        truckTest2.setType(FoodTypes.KEBAB);
        truckTest2.setMenu("Menu");
        truckTest2.setName("Name");

        SetrouteTest = new LinkedHashSet<>();
        SetrouteTest.add(routeTest);
        SetrouteTest.add(routeTest);
        truckTest2.setRoute(SetrouteTest);
    }

    @Test
    public void findByTruck() {

        when(routeRepo.findByTruck_id(truckTest2.getId())).thenReturn((List<Route>) SetrouteTest);
        List<Route> route = routeRepo.findByTruck_id(truckTest2.getId());

        for(Route r: route ) {
            assertAll(() -> assertTrue((r.getDate() == routeTest.getDate())),
                    () -> assertEquals(r.getId(), routeTest.getId()));
        }


    }


}
