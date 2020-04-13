package jeaps.foodtruck.common.user.user;

import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * This class is to be used as a means of doing database operations for User objects
 */
@Repository
public class UserDAO {


    //Repository of user objects
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ImageDAO imageDAO;

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Saves a user object into the database
     * @param u The user to be saved
     */
    public void save(User u){
        this.userRepo.save(u);
    }

    /**
     * Creates a user from a userDTO and saves it to the database
     *
     * @param u The userDTO to get the user details from
     * @return The ID of the user saved into the database
     */
    public Integer save(UserDTO u){
        //Initialise the user to be put into the database
        User user = new User();

        //Set the username, password, name, and email respectively
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setName(u.getName());
        user.setEmail(u.getEmail());

        //Save the user to the database
        this.userRepo.save(user);

        //Return the user's ID
        return user.getId();
    }

    /**
     * Updates the info of a specific user in the database
     *
     * @param u The userDTO that the new user info comes from
     */
    public void update(UserDTO u){
        //Find the user based on the username... They cannot change their username
        User user = this.userRepo.findByUsername(u.getUsername());

        //Set the values for the user from the given values if they are not null
        if(u.getEmail() != null) {
            user.setEmail(u.getEmail());
        }
        if(u.getName() != null) {
            user.setName(u.getName());
        }

        //Should you be able to update the password?
        if(u.getPassword() != null) {
            user.setPassword(u.getPassword());
        }

        //Save with the id will auto update to the new user info
        this.userRepo.save(user);
    }

    /**
     * Searches for a user in the database based on a given username.
     * @param username The username of the user to find
     * @return A User object created from the data in the database
     */
    public User findByUsername(String username){
        User u = this.userRepo.findByUsername(username);
        u.setProfilePicture(null);
        return u;
    }


    public Optional<User> findById(Integer id) {
        Optional<User> u =  this.userRepo.findById(id);
        u.ifPresent(user -> user.setProfilePicture(null));
        return u;
    }

    public Iterable<User> findAll() {
        Iterable<User> u = this.userRepo.findAll();
        u.forEach(user -> user.setProfilePicture(null));
        return u;
    }


    public Image saveProfile(String username, MultipartFile file) {
        User user = this.userRepo.findByUsername(username);

        if(user.getProfilePicture() != null) {
            this.deleteProfile(username);
        }
        Image i = this.imageDAO.saveFile(file);

        user.setProfilePicture(i);
        this.userRepo.save(user);
        return i;

    }

    public Image getProfile(String username) {
        User user = this.userRepo.findByUsername(username);
        return user.getProfilePicture();

    }

    public void deleteProfile(String username) {
        User user = this.userRepo.findByUsername(username);

        Image i = user.getProfilePicture();
        if(i != null) {
            this.imageDAO.deleteFile(i.getId());
        }
        user.setProfilePicture(null);
        this.userRepo.save(user);


    }

}


