package avalith.votingAPI.service;

import avalith.votingAPI.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    /***
     * getUser() service returns a User object filtered by id
     * @param id
     * @return User user
     */
    User getUser(long id);

    /***
     * getUsers Service returns all User objects
     * @param
     * @return List<User>
     */
    List<User> getUsers();

    /***
     * save(User user) Service saves in the database a new User object with a set of assigned roles, the default rol is "EMPLOYEE"
     * @param user
     * @return User user, Set<Role> roles
     */
    User save(User user);

    /***
     * getUserByName(String userName) Service get a User object by its username, which is unique
     * @param name
     * @return User user
     */
    User getUserByName (String name);

    /***
     * isAdmin() Service returns true if current session User is Admin. Otherwise, returns false
     * @param user
     * @return boolean
     */
    boolean isAdmin(User user);

}
