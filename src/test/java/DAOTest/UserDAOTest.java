package DAOTest;

import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserDAO.class, UserRepository.class})
//@DataJpaTest
//@ContextConfiguration()
//@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {


    @Autowired
    //@Mock
    private UserDAO userDAO;

    //@Rule
    //public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void saveUserTest() {
        User user = new User();

        user.setUsername("username");
        user.setPassword("password");
        user.setName("name");
        user.setEmail("email");

        System.out.println(user.getUsername());
        userDAO.save(user);

        User userTest = userDAO.findByUsername("username");
        System.out.println(userTest.getUsername());
       /* assertAll(() -> assertEquals(userTest.getUsername(), user.getUsername()),
                () -> assertEquals(userTest.getEmail(), user.getEmail()),
                () -> assertEquals(userTest.getId(), user.getId()),
                () -> assertEquals(userTest.get))*/
       assertEquals(user, userTest);
    }


    @Test
    public void saveUserDTOTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("username2");
        userDTO.setPassword("password2");
        userDTO.setName("name2");
        userDTO.setEmail("email2");

        userDAO.save(userDTO);

        User userTest = userDAO.findByUsername(userDTO.getUsername());
        assertAll(() -> assertEquals(userTest.getUsername(), userDTO.getUsername()),
                () -> assertEquals(userTest.getEmail(), userDTO.getEmail()),
                () -> assertEquals(userTest.getName(), userDTO.getName()),
                () -> assertEquals(userTest.getPassword(), userDTO.getPassword()));
    }


}
