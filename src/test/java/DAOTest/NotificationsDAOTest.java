package DAOTest;

import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserRepository;
import jeaps.foodtruck.common.user.user.notification.Notifications;
import jeaps.foodtruck.common.user.user.notification.NotificationsDAO;
import jeaps.foodtruck.common.user.user.notification.NotificationsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the Notifications DAO
 */
public class NotificationsDAOTest {

    //@Autowired
    NotificationsRepository notificationsRepo;

    //@Autowired
    UserRepository userRepo;

    //@Autowired
    NotificationsDAO notificationsDAO = new NotificationsDAO();

    //@Autowired
    UserDAO userDAO = new UserDAO();

    protected Notifications notify;
    protected User userTest;

    @Before
    public void init(){
        //Mock the repositories
        notificationsRepo = mock(NotificationsRepository.class);
        userRepo = mock(UserRepository.class);

        //Set the User DAO to use the mock user repository
        userDAO.setUserRepo(userRepo);

        //Set the values for the user
        userTest = new User();
        userTest.setUsername("username");
        userTest.setPassword("password");
        userTest.setName("name");
        userTest.setEmail("email");
        userDAO.save(userTest);

        //Set the values for the notification
        notify = new Notifications();
        notify.setSender("sender");
        notify.setBody("Body text");
        notify.setId(1);
        notify.setSubject("subject");
        notify.setType("Type");
        notify.setUser(userTest);
        notify.setCreateDateTime(new Date());
    }

    /**
     * Test sending a message from a named sender to all users
     */
    @Test
    @DisplayName("Test sending a message to all users")
    public void testSendAll(){

        //Mock the find all users function
        //when(notificationsDAO.sendAll(notify)).then(userTest.setNotifications(Arrays.asList(notify)));
        when(userDAO.findAll()).thenReturn(Arrays.asList(userTest, userTest, userTest));
        when(new NotificationsDAO()).thenReturn(notificationsDAO);
        notificationsDAO.sendAll(notify);

        //Send the notifications
        assertEquals(userTest.getNotifications().size(), 0);
    }
}
