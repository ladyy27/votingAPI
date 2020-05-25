package avalith.votingAPI.service;

import avalith.votingAPI.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(long id);

    List<User> getUsers();

    User save(User user);

    User getUserByName (String name);
}
