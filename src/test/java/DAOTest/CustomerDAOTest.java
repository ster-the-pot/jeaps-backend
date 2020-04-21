package DAOTest;

import jeaps.foodtruck.common.user.customer.CustomerDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Class to test the CustomerDAO
 */
public class CustomerDAOTest {

    @InjectMocks
    CustomerDAO cDAO;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test Scoring of trucks")

    public void testScores(){

    }
}
