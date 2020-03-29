package jeaps.foodtruck.common.user.customer.preferences;

import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PreferencesDAO {

    @Autowired
    private PreferencesRepository preferencesRepo;

    @Autowired
    private UserDAO userDAO;

    public void save(Preferences p){this.preferencesRepo.save(p);}

    public Optional<Preferences> findById(Integer id){return this.preferencesRepo.findById(id);}

}
