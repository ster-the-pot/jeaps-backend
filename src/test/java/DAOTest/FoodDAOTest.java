package DAOTest;

import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.food.FoodDAO;
import jeaps.foodtruck.common.truck.food.FoodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the functionality of the Food DAO
 */
public class FoodDAOTest {

    @Autowired
    private FoodDAO foodDAO = new FoodDAO();

    @Autowired
    private FoodRepository foodRepo;

    protected Food foodTest;

    @Before
    public void init(){
        foodRepo = mock(FoodRepository.class);

        foodTest = new Food("Kebab");
    }

    /**
     * Tests that the displayFood function will return all the foods
     */
    @Test
    @DisplayName("Test displaying all foods")
    public void testDisplayAll(){
        //Mock the function
        when(foodDAO.displayFood()).thenReturn(Arrays.asList(foodTest, foodTest, foodTest));

        //Validate the results
        for(Food f : foodDAO.displayFood()){
            assertEquals(foodTest.getFoodtype(), f.getFoodtype());
        }
    }

    /**
     * Test saving the food in the DB
     */
    @Test
    @DisplayName("Test save food")
    public void testSave(){
        //Mock the function
        when(foodRepo.save(any(Food.class))).thenReturn(new Food());

        //Test that a food can be saved
        foodDAO.saveFood(foodTest.getFoodtype());


    }

}
