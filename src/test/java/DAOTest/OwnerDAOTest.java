package DAOTest;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDTO;
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

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class OwnerDAOTest {


    @Autowired
    private OwnerDAO ownerDAO = new OwnerDAO();

    @Autowired
    private UserDAO userDAO = new UserDAO();

    @Autowired
    protected OwnerRepository ownerRepo;
    @Autowired
    protected UserRepository userRepo;

    protected User userTest = new User();
    protected Optional<Owner> ownerTest = Optional.of(new Owner());

    protected Truck truckTest = new Truck();

    @Before
    public void setUp() {
        ownerRepo = mock(OwnerRepository.class);
        ownerDAO.setOwnerRepo(ownerRepo);

        userRepo = spy(UserRepository.class);
        userDAO.setUserRepo(userRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");
        //userRepo.save(userTest);


        ownerTest.get().setId(userTest.getId());
        ownerRepo.save(ownerTest.get());



        truckTest.setMenu("Menu");
        truckTest.setName("Name");
    }

    /**
     * Tests the findByID() function
     */
    @Test
    @DisplayName("Test finding an owner by their ID")
    public void findByID() {

        when(ownerRepo.findById(ownerTest.get().getId())).thenReturn(ownerTest);
        Optional<Owner> owner = ownerDAO.findById(ownerTest.get().getId());

        assertAll(() -> assertEquals(ownerTest.get().getTrucks(), owner.get().getTrucks()),
                () -> assertEquals(ownerTest.get().getId(), owner.get().getId()));

    }
/*
    /**
     * Tests the getOwnerStatsByName() function
     *
    @Test
    @DisplayName("Test getting stats from owner using username")
    public void getOwnerStatsByName() {

        //attempt to get the owners stats given a username
        when(userRepo.findByUsername(userTest.getUsername())).thenReturn(userTest);
        //System.out.println(userDAO.findByUsername(userTest.getUsername()).getUsername());
        Map<String, Object> stats = ownerDAO.getOwnerStats("TestOwner");//userTest.getUsername());
        //validate the output
        assertAll(() -> assertEquals(ownerTest.get().getTrucks().size(), stats.get("Trucks")),
                () -> assertEquals(ownerTest.get().getSubscribers().size(), stats.get("Subscribers")),
                () -> assertEquals(ownerTest.get().getFoodTypes().size(), stats.get("FoodTypes")),
                () -> assertEquals(ownerTest.get().getAvgRating(), stats.get("AvgTruckRating")));
    }
*/
    /**
     * Tests the findAll() function for owners
     */
    @Test
    @DisplayName("Test finding all owners")
    public void testFindAll(){

        //Tell the function to return 3 copies of ownerTest when getting all owners
        when(ownerDAO.findAll()).thenReturn(Arrays.asList(ownerTest.get(), ownerTest.get(), ownerTest.get()));

        for(Owner o : ownerDAO.findAll()){
            assertAll(() -> assertEquals(ownerTest.get().getAvgRating(), o.getAvgRating()),
                      () -> assertEquals(ownerTest.get().getId(), o.getId()),
                      () -> assertEquals(ownerTest.get().getTrucks().size(), o.getTrucks().size()));
        }
    }


    /**
     * Tests that
     */
    @Test
    @DisplayName("Test saving truck to missing owner")
    public void testSaveTruckNoOwner(){
        TruckDTO truckDTO = new TruckDTO();
        when(userRepo.findByUsername("username")).thenReturn(userTest);
        assertFalse(ownerDAO.saveTruck(truckDTO, userTest.getUsername()));
    }
}
