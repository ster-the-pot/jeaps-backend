package ControllerTests;

import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.controllers.CustomerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Used to test the customer controller
 */
public class CustomerControllerTest {

    private static CustomerController cCntrl = null;
    private static Preferences pref = null;
    private static String username = null;

    @Before
    public void init(){
        cCntrl = new CustomerController();
        username = "Jimmy";

    }

    @Test
    @DisplayName("Test edit preferences")
    public void testEditPrefs(){
        String result = (String) cCntrl.editPreferences(pref, username);
        assertEquals("Successfully updated Preferences", result);
    }
}
