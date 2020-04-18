package ControllerTests;

import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import jeaps.foodtruck.common.truck.route.Route;
import jeaps.foodtruck.common.truck.route.RouteDAO;
import jeaps.foodtruck.common.truck.route.RouteDTO;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import jeaps.foodtruck.controllers.OwnerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Classs to test the Owner Controller
 */
public class OwnerControllerTest {

    @InjectMocks
    private static OwnerController oCntrl;

    @Mock
    private static OwnerDAO ownerDAO;

    @Mock
    private static UserDAO userDAO;

    @Mock
    private static TruckDAO truckDAO;

    @Mock
    private static ImageDAO imageDAO;

    @Mock
    private static RouteDAO routeDAO;

    @Mock
    private static UserDTO userDTO;

    @Mock
    private static TruckDTO truckDTO;

    @Mock
    private static RouteDTO routeDTO;

    //@Mock
    //private static MultipartFile[] file;

    private static String username = null;
    private static Integer tID = null;

    /**
     * Init the mocks and any other variables needed to test
     */
    @Before
    public void init(){
        username = "owner";
        tID = 1;
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test showing details")
    public void testDetails(){
        when(userDAO.findByUsername(anyString())).thenReturn(new User());
        assertNotNull(oCntrl.getUserDetails(username));
    }

    @Test
    @DisplayName("Test managing details")
    public void testManage(){
        assertEquals("Successfully updated Owner info", oCntrl.manageUserDetails(userDTO));
    }

    @Test
    @DisplayName("Test getting subscribers from truck")
    public void testGetSubsTruck(){
        when(truckDAO.getSubscribers(any(Integer.class))).thenReturn(new ArrayList<>());
        assertNotNull(oCntrl.getSubscribers(tID));
    }

    @Test
    @DisplayName("Test getting number of subscribers from truck")
    public void testGetNumSubsTruck(){
        assertNotNull(oCntrl.getNumSubscribers(tID));
    }

    @Test
    @DisplayName("Test getting subscribers from owner")
    public void testGetSubsOwner(){
        assertNotNull(oCntrl.getOwnerSubscribers(username));
    }

    @Test
    @DisplayName("Test getting number of subscribers from owner")
    public void testGetNumSubsOwner(){
        assertNotNull(oCntrl.getNumOwnerSubscribers(username));
    }

    @Test
    @DisplayName("Test deleting menu")
    public void testDeleteMenu(){
        assertEquals("Menu successfully deleted", oCntrl.deleteMenu(tID).getBody());
    }

    @Test
    @DisplayName("Test getting menu")
    public void testGetMenu(){
        assertNotNull(oCntrl.getMenu(tID));
    }

    @Test
    @DisplayName("Test deleting a picture")
    public void testDeletePicture(){
        assertEquals("Picture successfully deleted", oCntrl.deletePicture(tID, username).getBody());
    }

    @Test
    @DisplayName("Test deleting all pictures")
    public void testDeleteAllPictures(){
        assertEquals("Pictures successfully deleted", oCntrl.deleteAllPicture(tID).getBody());
    }

    @Test
    @DisplayName("Test getting picture IDs")
    public void testGetPicIDs() throws IOException {
        assertNotNull(oCntrl.getPictures(tID));
    }

    @Test
    @DisplayName("Test creating truck")
    public void testCreateTruck(){
        assertNotNull(oCntrl.createTruck(truckDTO, username));
    }

    @Test
    @DisplayName("Test deleting truck")
    public void testDeleteTruck(){
        assertEquals("Successfully deleted truck", oCntrl.deleteTruck(tID));
    }

    @Test
    @DisplayName("Test listing trucks")
    public void testFindTruck(){
        assertNotNull(oCntrl.findTruck(username));
    }

    @Test
    @DisplayName("Test editing truck")
    public void testEditTruck(){
        assertEquals("Successfully updated truck", oCntrl.editTruck(truckDTO, tID));
    }

    @Test
    @DisplayName("Test create route")
    public void testCreateRoute(){
        when(routeDAO.saveRoute(any(RouteDTO.class), any(Integer.class), anyString())).thenReturn(new Route());
        assertNotNull(oCntrl.createRoute(routeDTO, tID, username));
    }

    @Test
    @DisplayName("Test modifying routes")
    public void testModifyRoute(){
        when(routeDAO.saveRoute(any(RouteDTO.class), any(Integer.class), anyString())).thenReturn(new Route());
        assertNotNull(oCntrl.editRoute(routeDTO));
    }

    @Test
    @DisplayName("Test getting routes")
    public void testGetRoutes(){
        assertNotNull(oCntrl.getRoutes(tID));
    }

    @Test
    @DisplayName("Test deleting a route")
    public void testDeleteRoute(){
        assertEquals("Successfully deleted route", oCntrl.deleteRoute(tID));
    }

    @Test
    @DisplayName("Test deleting all routes")
    public void testDeleteAllRoutes(){
        assertEquals("Successfully deleted route", oCntrl.deleteAllRoute(new ArrayList<Integer>()));
    }

    @Test
    @DisplayName("Test editting routes")
    public void testEditRoute(){
        assertEquals("Successfully updated truck", oCntrl.editRoute(routeDTO));
    }
}
