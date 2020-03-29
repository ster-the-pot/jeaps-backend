package jeaps.foodtruck.common.user.user;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

/**
 * This class represents an authorised user within the system. The User object will contain all of the information of
 *    the authorised user, and the inclusion of the user's ID in either the customer or owner table will denote
 *    what type of authorised user they are.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-generated id to ensure unique users
    private Integer id;
    // The unique user/screen name
    @Column(unique=true)
    private String username;
    // The user's real name
    private String name;
    // The user's email
    private String email;
    // The user's password
    //TODO: hash this so that security is at least half-@ssed
    private String password;

    /**
     * Returns the user's username
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username. Should be used in gathering and creation of user objects, but once set, the
     *    username should remain constant.
     * @param username The username to be associated with a user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's unique ID
     * @return the user's unique ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's ID
     * @param id The ID to be assigned to the user
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the user's real name
     * @return the user's real name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's real name
     * @param name The name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's email address
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address
     * @param email The email address to be set for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password The password to be set for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
