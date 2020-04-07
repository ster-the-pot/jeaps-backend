package DAOTest;

import jeaps.foodtruck.common.truck.food.Food;
import jeaps.foodtruck.common.truck.food.FoodDAO;
import jeaps.foodtruck.common.truck.food.FoodRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mock;

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


    }

}
