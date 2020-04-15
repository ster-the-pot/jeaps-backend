package ControllerTests;

import jeaps.foodtruck.common.user.user.notification.Notifications;
import jeaps.foodtruck.common.user.user.notification.NotificationsDAO;
import jeaps.foodtruck.controllers.NotificationsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class used to test the NotificationsController
 */
public class NotificationsControllerTest {

    @InjectMocks
    private static NotificationsController notiCntrl;

    @Mock
    private static NotificationsDAO notiDAO;

    @Mock
    private static Notifications noti;

    private static String username = null;
    private static Integer tID = null;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        username = "Jimmy";
        tID = 1;
    }

    @Test
    @DisplayName("Test sending notifications to everyone")
    public void testSendAll(){
        assertEquals("Successfully sent message to all", notiCntrl.sendAll(noti));
    }

    @Test
    @DisplayName("Test sending notifications to all owners")
    public void testSendAllOwners(){
        assertEquals("Successfully sent message to all Owners", notiCntrl.sendAllOwners(noti));
    }

    @Test
    @DisplayName("Test sending notifications to all customers")
    public void testSendAllCustomers(){
        assertEquals("Successfully sent message to all Customers", notiCntrl.sendAllCustomers(noti));
    }

    @Test
    @DisplayName("Test sending notifications to all subscribers of an owner")
    public void testSendSubscribers(){
        assertEquals("Successfully sent message to all Subscribers", notiCntrl.sendAllSubscribers(noti, username));
    }

    @Test
    @DisplayName("Test sending notifications to all subscribers of a truck")
    public void testSendTruckSubscribers(){
        assertEquals("Successfully sent message to all Truck Subscribers", notiCntrl.sendAllTruckSubscribers(noti, username, tID));
    }

    @Test
    @DisplayName("Test sending notification to a single user")
    public void testSendOneUser(){
        assertEquals("Successfully sent message to single User", notiCntrl.sendUser(noti, username));
    }

    @Test
    @DisplayName("Test clearing a notification")
    public void testClearNotification(){
        assertEquals("Successfully removed notification", notiCntrl.removeNotification(tID));
    }

    @Test
    @DisplayName("Test clearing notifications for a user")
    public void testClearUserNotifications(){
        assertEquals("Successfully removed all users notifications", notiCntrl.removeNotification(username));
    }
}
