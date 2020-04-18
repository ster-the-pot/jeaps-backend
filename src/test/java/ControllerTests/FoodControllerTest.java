package ControllerTests;

import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.food.FoodDAO;
import jeaps.foodtruck.controllers.FoodController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Used to test the Food Controller
 */
public class FoodControllerTest {

    @InjectMocks
    private static FoodController fCntrl;

    @Mock
    private static FoodDAO foodDAO;

    private static String food = null;

    /**
     * Used to initialise the mocks for testing, as well as any other relevant variables
     */
    @Before
    public void init(){
        food = "Kebab";
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test adding food")
    public void testAddFood(){
        assertEquals("Successfully added a Food type", fCntrl.addFood(food).getBody());
    }

    @Test
    @DisplayName("Test adding multiple foods")
    public void testAddListFood(){
        assertEquals("Successfully added a List of Food types", fCntrl.addListFood(new ArrayList<String>()).getBody());
    }

    @Test
    @DisplayName("Test deleting food")
    public void testDeleteFood(){
        assertEquals("Successfully deleted a Food type", fCntrl.deleteFood(food).getBody());
    }

    @Test
    @DisplayName("Test displaying food")
    public void testDisplayFood(){
        when(foodDAO.displayFood()).thenReturn(new ArrayList<Food>());
        assertNotNull(fCntrl.display());
    }


}
