package avalith.votingAPI.service;

import avalith.votingAPI.mapper.UserDetailsMapper;
import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import avalith.votingAPI.repository.RoleRepository;
import avalith.votingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/***
 * UserServiceImpl implements all UserService Methods
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private AuthenticationService authenticationFacade;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /***
     * Method that enables login functionality in the voting system, recovering User details
     * @param userName
     * @return UserDetailsMapper
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User retrievedUser = userRepository.findByName(userName);
        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return UserDetailsMapper.build(retrievedUser);
    }

    /***
     * getUser(long id) find all data from one User filtering by their id
     * @param id
     * @return User user
     */
    @Override
    public User getUser(long id) {
        return userRepository.findById(id);
    }

    /***
     * getUsers() find all users data
     * @return List<User>
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /***
     * save(User user) saves in the database a new User object with a set of assigned roles, the default rol is "EMPLOYEE"
     * @param user
     * @return User user, Set<Role> roles
     */
    @Override
    public User save(User user) {
        Role userRole = roleRepository.findByName("EMPLOYEE");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User userToSave = User.builder().name(user.getName()).password(user.getPassword()).roles(roles).build();

        return userRepository.save(userToSave);
    }

    /***
     * getUserByName(String userName) get a User object by its username, which is unique
     * @param userName
     * @return
     */
    @Override
    public User getUserByName(String userName) {
        final User user = userRepository.findByName(userName);
        return user;
    }

    /***
     * isAdmin() Service returns true if current session User is Admin. Otherwise, returns false
     * @param user
     * @return boolean
     */
    @Override
    public boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();

        for (Iterator<Role> it = roles.iterator(); it.hasNext(); ) {
            Role role = it.next();
            if (role.getName().equals("ADMIN"))
                return true;
        }
        return false;
    }
}
