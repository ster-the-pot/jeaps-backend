package DAOTest;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.owner.OwnerRepository;
import jeaps.foodtruck.common.user.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerDAOTest {


    @Autowired
    private OwnerDAO ownerDAO = new OwnerDAO();

    OwnerRepository ownerRepo;

    User userTest = new User();
    Optional<Owner> ownerTest = Optional.of(new Owner());
    Truck truckTest = new Truck();
    @Before
    public void setUp() {
        ownerRepo = mock(OwnerRepository.class);
        ownerDAO.setOwnerRepo(ownerRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");

        ownerTest.get().setId(userTest.getId());

        truckTest.setType("Type");
        truckTest.setMenu("Menu");
        truckTest.setName("Name");
    }

    @Test
    public void findByID() {

        when(ownerRepo.findById(ownerTest.get().getId())).thenReturn(ownerTest);
        Optional<Owner> owner = ownerDAO.findById(ownerTest.get().getId());

        assertAll(() -> assertEquals(ownerTest.get().getTrucks(), owner.get().getTrucks()),
                () -> assertEquals(ownerTest.get().getId(), owner.get().getId()));

    }


}
