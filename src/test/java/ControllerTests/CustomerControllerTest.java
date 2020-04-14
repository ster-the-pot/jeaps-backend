package ControllerTests;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.controllers.CustomerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Used to test the customer controller
 */
public class CustomerControllerTest {

    @InjectMocks
    private static CustomerController cCntrl;

    @Mock
    private static CustomerDAO customerDAO;

    @Mock
    private static Preferences pref;

    @Mock
    private static UserDAO userDAO;

    @Mock
    private static UserDTO userDTO;

    private static String username = null;
    private static Integer tID = null;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        username = "Jimmy";
        tID = 1;
    }

    @Test
    @DisplayName("Test getting preferences")
    public void testGetPrefs(){
        when(customerDAO.getPreferences(anyString())).thenReturn(new HashMap<String, Object>());

        assertNotNull(cCntrl.getPreferences(username));
    }

    @Test
    @DisplayName("Test edit preferences")
    public void testEditPrefs(){
        String result = (String) cCntrl.editPreferences(pref, username);
        assertEquals("Successfully updated Preferences", result);
    }

    @Test
    @DisplayName("Test displaying user details")
    public void testDisplayDetails(){
        User details = null;
        when(userDAO.findByUsername(anyString())).thenReturn(new User());
        details = cCntrl.getUserDetails(username);

        assertNotNull(details);
    }

    @Test
    @DisplayName("Test managing user details")
    public void testEditDetails(){
        String result = (String) cCntrl.manageUserDetails(userDTO);

        assertEquals("Successfully updated Customer info", result);
    }

    @Test
    @DisplayName("Test getting all subscribed trucks")
    public void testGetSubs(){
        when(customerDAO.getSubscribedTrucks(anyString())).thenReturn(new ArrayList<Truck>());

        assertNotNull(cCntrl.getSubscribedTrucks(username));
    }

    @Test
    @DisplayName("Test subscribing to a truck")
    public void testSubTo(){
        when(customerDAO.subscribeToTruck(anyString(), any(Integer.class))).thenReturn(Optional.of(new Truck()));

        assertNotNull(((Optional<Truck>)cCntrl.subscribeToTruck(username, tID)).get());
    }

    @Test
    @DisplayName("Test unsubscribing from a truck")
    public void testUnsubTo(){
        assertEquals("Successfully unsubscribed to truck", cCntrl.unsubscribeToTruck(username, tID));
    }

    @Test
    @DisplayName("Test getting recommendations")
    public void testGetRecs(){
        when(customerDAO.getRecommendations(anyString())).thenReturn(new ArrayList<Truck>());

        assertNotNull(cCntrl.getRecommendation(username));
    }
}
