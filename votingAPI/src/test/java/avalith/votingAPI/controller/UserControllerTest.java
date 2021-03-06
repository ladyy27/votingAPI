package avalith.votingAPI.controller;

import avalith.votingAPI.model.User;
import avalith.votingAPI.model.UserResponse;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private static final long USER_ID = 1L;

    public static final String USER_NAME = "USER_NAME";

    public static final String USER_PASSWORD = "USER_PASSWORD";

    private static final String ENCRYPTED_PASSWORD = "ENCRYPTED_PASSWORD";

    @Mock
    private UserServiceImpl userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserController userController;

    @Test
    public void callServiceGetAllUsers() {
        final User user = User.builder().id(USER_ID).name(USER_NAME).password(USER_PASSWORD).build();
        when(userService.getUser(USER_ID)).thenReturn(user);
        final ResponseEntity<UserResponse> userResponse = userController.getUser(USER_ID);

        assertThat(userResponse.getBody()).isNotNull();

        UserResponse retrievedUser = userResponse.getBody();
        assertThat(retrievedUser.getId()).isEqualTo(user.getId());
        assertThat(retrievedUser.getName()).isEqualTo(user.getName());
    }

    @Test
    public void callServiceGetUserShouldReturnNotFound() {
        final ResponseEntity<UserResponse> userResponse = userController.getUser(USER_ID);

        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void callServiceSaveUser() {
        final AuthorizationRequest authorizationRequest = AuthorizationRequest.builder().username(USER_NAME)
                .password(USER_PASSWORD).build();
        final User userToSave = User.builder().name(USER_NAME).password(ENCRYPTED_PASSWORD).build();
        final User savedUser = User.builder().id(USER_ID).name(USER_NAME).password(USER_PASSWORD).build();

        when(bCryptPasswordEncoder.encode(USER_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        when(userService.save(userToSave)).thenReturn(savedUser);

        final ResponseEntity<User> userResponse = userController.saveUser(authorizationRequest);
        assertThat(userResponse.getBody()).isEqualTo(savedUser);
        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void callServiceGetTotalUsers() {
        final List<User> users = userController.getUsers();
        when(userService.getUsers()).thenReturn(users);

        assertThat(users.size()).isNotNegative();
    }
}
