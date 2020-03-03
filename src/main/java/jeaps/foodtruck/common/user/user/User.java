package jeaps.foodtruck.common.user.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public String getUsername() { return username; }

    /**
     * Sets the user's username. Should be used in gathering and creation of user objects, but once set, the
     *    username should remain constant.
     * @param username The username to be associated with a user
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Returns the user's unique ID
     * @return the user's unique ID
     */
    public Integer getId() { return id; }

    /**
     * Sets the user's ID
     * @param id The ID to be assigned to the user
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Returns the user's real name
     * @return the user's real name
     */
    public String getName() { return name; }

    /**
     * Sets the user's real name
     * @param name The name of the user
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the user's email address
     * @return the user's email address
     */
    public String getEmail() { return email; }

    /**
     * Sets the user's email address
     * @param email The email address to be set for the user
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the user's password
     * @return the user's password
     */
    public String getPassword() { return password; }

    /**
     * Sets the user's password
     * @param password The password to be set for the user
     */
    public void setPassword(String password) { this.password = password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, password);
    }
}
