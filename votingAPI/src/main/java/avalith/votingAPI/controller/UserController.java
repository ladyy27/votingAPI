package avalith.votingAPI.controller;

import avalith.votingAPI.mapper.UserMapper;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import avalith.votingAPI.repository.VoteRepository;
import avalith.votingAPI.service.UserService;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.security.UserResponse;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        final User user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = UserMapper.toResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody AuthorizationRequest userRequest) {
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        final User userToSave = userService.save(UserMapper.toDomain(userRequest));

        return new ResponseEntity<>(userToSave, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getUsers() {
        final List<User> users = userService.getUsers();
        return users;
    }
}
