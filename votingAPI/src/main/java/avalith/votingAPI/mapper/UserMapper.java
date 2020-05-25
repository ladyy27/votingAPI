package avalith.votingAPI.mapper;

import avalith.votingAPI.model.User;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.security.UserResponse;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder().name(user.getName()).id(user.getId()).build();
    }

    public static User toDomain(AuthorizationRequest authorizationRequest) {
        return User.builder().name(authorizationRequest.getUsername()).password(authorizationRequest.getPassword())
                .build();
    }
}

