package avalith.votingAPI.controller;

import avalith.votingAPI.mapper.UserMapper;
import avalith.votingAPI.model.User;
import avalith.votingAPI.service.UserService;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getUsers() {
        final List<User> users = userService.getUsers();
        return users;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        User user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        final UserResponse userResponse = UserMapper.toResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/total")
    public int getTotalUsers() {
        int totalUsers = getUsers().size();
        return totalUsers;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody AuthorizationRequest userRequest) {
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        final User userToSave = userService.save(UserMapper.toDomain(userRequest));

        return new ResponseEntity<>(userToSave, HttpStatus.OK);
    }
}
