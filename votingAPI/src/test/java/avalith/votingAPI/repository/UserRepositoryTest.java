package avalith.votingAPI.repository;

import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class UserRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void getUserByUserNameTest() {
        User retrievedUser = userRepository.findByName("user1");

        assertThat(retrievedUser).isNotNull();
    }

    @Test
    public void getUserByIdTest() {
        User retrievedUser = userRepository.findById(1);

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getName()).isEqualTo("user1");
    }

    @Test
    public void getAllUsersTest() {
        final List<User> users = userRepository.findAll();

        assertThat(users).hasSize(7);
    }

    @Test
    public void saveUserWithAssociatedRolesTest() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleById(2));

        User newUser = User.builder().name("NEWUSER").password("PASSWORD").roles(roles).build();
        User savedUser = userRepository.save(newUser);

        final User retrievedUser = userRepository.findById(savedUser.getId());

        assertThat(retrievedUser).isEqualTo(savedUser);
    }


}
