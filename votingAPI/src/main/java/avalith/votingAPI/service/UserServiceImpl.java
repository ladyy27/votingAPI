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
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User retrievedUser = userRepository.findByName(userName);
        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return UserDetailsMapper.build(retrievedUser);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Role userRole = roleRepository.findByName("EMPLOYEE");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User userToSave = User.builder().name(user.getName()).password(user.getPassword()).roles(roles).build();

        return userRepository.save(userToSave);
    }

    @Override
    public User getUserByName(String userName) {
        final User user = userRepository.findByName(userName);
        return user;
    }


}
