package DAOTest;

import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

    //@Mock UserRepository userRepo;
    //@InjectMocks UserDAO userDAO;


    @Autowired
    private UserDAO userDAO = new UserDAO();

    UserRepository userRepo;

    User userTest = new User();
    @Before
    public void setUp() {
        userRepo = mock(UserRepository.class);
        userDAO.setUserRepo(userRepo);

        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");
    }

    @Test
    public void findByUsernameTest() {

        User user = new User();

        user.setUsername("username");
        user.setPassword("password");
        user.setName("name");
        user.setEmail("email");

        when(userRepo.findByUsername(user.getUsername())).thenReturn(userTest);
        User userTest2 = userDAO.findByUsername(user.getUsername());

        assertAll(() -> assertEquals(userTest.getUsername(), userTest2.getUsername()),
                () -> assertEquals(userTest.getEmail(), userTest2.getEmail()),
                () -> assertEquals(userTest.getName(), userTest2.getName()),
                () -> assertEquals(userTest.getPassword(), userTest2.getPassword()));
    }


    @Test
    public void saveUserDTOTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setName("name");
        userDTO.setEmail("email");

        when(userRepo.save(any(User.class))).thenReturn(new User());

        userDAO.save(userDTO);

        when(userRepo.findByUsername(userDTO.getUsername())).thenReturn(userTest);
        User userTest = userDAO.findByUsername(userDTO.getUsername());

        assertAll(() -> assertEquals(userTest.getUsername(), userDTO.getUsername()),
                () -> assertEquals(userTest.getEmail(), userDTO.getEmail()),
                () -> assertEquals(userTest.getName(), userDTO.getName()),
                () -> assertEquals(userTest.getPassword(), userDTO.getPassword()));
    }


    @Test
    @DisplayName("Test finding by ID")
    public void testFindByID(){
        when(userRepo.findById(userTest.getId())).thenReturn(Optional.of(userTest));

        Optional<User> u = userDAO.findById(userTest.getId());

        assertAll(() -> assertEquals(userTest.getId(), u.get().getId()),
                () -> assertEquals(userTest.getUsername(), u.get().getUsername()),
                () -> assertEquals(userTest.getPassword(), u.get().getPassword()),
                () -> assertEquals(userTest.getEmail(), u.get().getEmail()),
                () -> assertEquals(userTest.getName(), u.get().getName()));
    }

    @Test
    @DisplayName("Test finding all users")
    public void testFindAll(){
        when(userRepo.findAll()).thenReturn(Arrays.asList(userTest, userTest, userTest));

        Iterable<User> allUsers = userDAO.findAll();

        for(User u : allUsers) {
            assertAll(() -> assertEquals(userTest.getUsername(), u.getUsername()),
                    () -> assertEquals(userTest.getName(), u.getName()),
                    () -> assertEquals(userTest.getId(), u.getId()),
                    () -> assertEquals(userTest.getEmail(), u.getEmail()),
                    () -> assertEquals(userTest.getPassword(), u.getPassword()));
        }
    }

}
