package avalith.votingAPI.mapper;

import avalith.votingAPI.model.User;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.model.UserResponse;

public class UserMapper {

    private UserMapper() {
    }

    /***
     * toResponse returns UserResponse object (name, id)
     * @param user
     * @return UserResponse object
     */
    public static UserResponse toResponse(User user) {
        return UserResponse.builder().name(user.getName()).id(user.getId()).build();
    }

    /***
     * toDomain returns User object based on AuthorizationRequest credentials
     * @param authorizationRequest
     * @return
     */
    public static User toDomain(AuthorizationRequest authorizationRequest) {
        return User.builder().name(authorizationRequest.getUsername()).password(authorizationRequest.getPassword())
                .build();
    }
}

