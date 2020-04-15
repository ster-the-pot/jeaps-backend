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

/**
 * Used to test the Food Controller
 */
public class FoodControllerTest {

    @InjectMocks
    FoodController fCntrl;

    @Mock
    FoodDAO foodDAO;

    /**
     * Used to initialise the mocks for testing, as well as any other relevant variables
     */
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test adding food")
    public void testAddFood(){

    }

}
