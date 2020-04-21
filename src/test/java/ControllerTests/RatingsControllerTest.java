package ControllerTests;

import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.truck.rate.RateDAO;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import jeaps.foodtruck.controllers.RatingsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Class to test the Ratings Controller
 */
public class RatingsControllerTest {

    @InjectMocks
    private static RatingsController rCntrl;

    @Mock
    private static RateDAO rateDAO;

    @Mock
    private static ImageDAO imageDAO;

    @Mock
    private static RateDTO rateDTO;

    @Mock
    private static TokenService toki;

    @Mock
    private static HttpServletRequest httpserv;

    private static String username = null;
    private static Integer tID = null;

    /**
     * Initialise mocks and any other relevant variables
     */
    @Before
    public void init(){

        username = "username";
        tID = 1;

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test getting ratings")
    public void testGetRatings(){
        when(toki.getUsername(any(HttpServletRequest.class))).thenReturn(username);
        assertNotNull(rCntrl.getRatings(httpserv));
    }

    @Test
    @DisplayName("Test adding a rating")
    public void testAddRating(){
        when(toki.getUsername(any(HttpServletRequest.class))).thenReturn(username);
        assertEquals("Rating successfully added", rCntrl.addRating(rateDTO, tID, httpserv).getBody());
    }

    @Test
    @DisplayName("Test editing a rating")
    public void testEditRating(){
        when(toki.getUsername(any(HttpServletRequest.class))).thenReturn(username);
        assertEquals("Rating successfully edited", rCntrl.editRating(rateDTO, tID, httpserv).getBody());
    }

    @Test
    @DisplayName("Test deleting a rating")
    public void testDeleteRarting(){
        when(toki.getUsername(any(HttpServletRequest.class))).thenReturn(username);
        assertEquals("Rating successfully deleted", rCntrl.editRating(tID, httpserv).getBody());
    }

    @Test
    @DisplayName("Test getting average rating")
    public void testGetAverage(){
        when(rateDAO.getAvgRating(any(Integer.class))).thenReturn(1.0);
        Map<String, Double> m = (Map<String, Double>) rCntrl.getAverageRating(tID).getBody();
        assertAll(()-> assertEquals(true, m.containsKey("Ratings")),
                ()->assertEquals(1.0, m.get("Ratings")));
    }

    @Test
    @DisplayName("Test getting all ratings of a truck")
    public void testGetAllRatings(){
        assertNotNull(rCntrl.getFullTruckRatings(tID).getBody());
    }

    @Test
    @DisplayName("Test getting all number ratings of a truck")
    public void testGetAllNumRatings(){
        assertNotNull(rCntrl.getTruckRatings(tID).getBody());
    }

    @Test
    @DisplayName("Test deleting a picture")
    public void testDeletePicture(){
        assertEquals("Picture successfully deleted", rCntrl.deletePicture(tID, username, username).getBody());
    }

    @Test
    @DisplayName("Test deleting multiple pictures")
    public void testDeletePictures(){
        assertEquals("Pictures successfully deleted", rCntrl.deleteAllPicture(tID, username).getBody());
    }

    @Test
    @DisplayName("Test getting picture IDs")
    public void testGetAllPictures() throws IOException {
        when(rateDAO.getPictureIds(any(Integer.class), anyString())).thenReturn(new ArrayList<String>());
        assertNotNull(rCntrl.getPictures(tID, username));
    }
}
