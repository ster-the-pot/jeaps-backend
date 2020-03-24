package DAOTest;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckRepository;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.owner.OwnerRepository;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TruckDAOTest {

    @Autowired
    private TruckDAO truckDAO = new TruckDAO();



    TruckRepository truckRepo;



    User userTest = new User();
    Optional<Owner> ownerTest = Optional.of(new Owner());
    Optional<Truck> truckTest = Optional.of(new Truck());
    Truck truckTest2 = new Truck();

    @Before
    public void setUp() {
        truckRepo = mock(TruckRepository.class);
        truckDAO.setTruckRepo(truckRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");

        ownerTest.get().setId(userTest.getId());

        truckTest.get().setType("Type");
        truckTest.get().setMenu("Menu");
        truckTest.get().setName("Name");

        truckTest2.setType("Type");
        truckTest2.setMenu("Menu");
        truckTest2.setName("Name");
    }

    @Test
    public void findByID() {

        when(truckRepo.findById(truckTest.get().getId())).thenReturn(truckTest);
        Optional<Truck> truck = truckDAO.findById(truckTest.get().getId());

        assertAll(() -> assertEquals(truckTest.get().getType(), truck.get().getType()),
                () -> assertEquals(truckTest.get().getRoute(), truck.get().getRoute()),
                () -> assertEquals(truckTest.get().getMenu(), truck.get().getMenu()),
                () -> assertEquals(truckTest.get().getName(), truck.get().getName()),
                () -> assertEquals(truckTest.get().getId(), truck.get().getId()));

    }

    @Test
    public void findByName() {

        when(truckRepo.findByName(truckTest2.getName())).thenReturn(truckTest2);
        Truck truck = truckDAO.findByName(truckTest2.getName());

        assertAll(() -> assertEquals(truckTest2.getType(), truck.getType()),
                () -> assertEquals(truckTest2.getRoute(), truck.getRoute()),
                () -> assertEquals(truckTest2.getMenu(), truck.getMenu()),
                () -> assertEquals(truckTest2.getName(), truck.getName()),
                () -> assertEquals(truckTest2.getId(), truck.getId()));

    }

    @Test
    public void findAll() {

        when(truckRepo.findAll()).thenReturn(Arrays.asList(truckTest2, truckTest2, truckTest2));
        List<Truck> truck = truckDAO.findALL();

        for(Truck t: truck) {
            assertAll(() -> assertEquals(truckTest2.getType(), t.getType()),
                    () -> assertEquals(truckTest2.getRoute(), t.getRoute()),
                    () -> assertEquals(truckTest2.getMenu(), t.getMenu()),
                    () -> assertEquals(truckTest2.getName(), t.getName()),
                    () -> assertEquals(truckTest2.getId(), t.getId()));
        }


    }


}
