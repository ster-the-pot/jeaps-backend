package DAOTest;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.owner.OwnerRepository;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerDAOTest {


    @Autowired
    private OwnerDAO ownerDAO = new OwnerDAO();

    @Autowired
    private UserDAO userDAO = new UserDAO();

    protected OwnerRepository ownerRepo;
    protected UserRepository userRepo;

    protected User userTest = new User();
    protected Optional<Owner> ownerTest = Optional.of(new Owner());
    protected Truck truckTest = new Truck();

    @Before
    public void setUp() {
        ownerRepo = mock(OwnerRepository.class);
        ownerDAO.setOwnerRepo(ownerRepo);

        userRepo = mock(UserRepository.class);
        userDAO.setUserRepo(userRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");



        ownerTest.get().setId(userTest.getId());


        truckTest.setMenu("Menu");
        truckTest.setName("Name");
    }

    @Test
    @DisplayName("Test finding an owner by their ID")
    public void findByID() {
        System.out.println(userTest.getUsername());
        when(ownerRepo.findById(ownerTest.get().getId())).thenReturn(ownerTest);
        Optional<Owner> owner = ownerDAO.findById(ownerTest.get().getId());

        assertAll(() -> assertEquals(ownerTest.get().getTrucks(), owner.get().getTrucks()),
                () -> assertEquals(ownerTest.get().getId(), owner.get().getId()));

    }

    @Test
    @DisplayName("Test getting stats from owner using username")
    public void getOwnerStatsByName() {
        System.out.println(userTest.getUsername());
        //attempt to get the owners stats given a username
        Map<String, Object> stats = ownerDAO.getOwnerStats(userTest.getUsername());

        //Get all of the owner's subscribers


        //validate the output
        assertAll(() -> assertEquals(ownerTest.get().getTrucks().size(), stats.get("Trucks")),
                () -> assertEquals(ownerTest.get().getSubscribers().size(), stats.get("Subscribers")),
                () -> assertEquals(ownerTest.get().getFoodTypes().size(), stats.get("FoodTypes")),
                () -> assertEquals(ownerTest.get().getAvgRating(), stats.get("AvgTruckRating")));
    }


}
