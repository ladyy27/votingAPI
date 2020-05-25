package avalith.votingAPI.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class AuthorizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 'username', 'password' are the keys for /login access
     */
    @JsonProperty("username")
    private String username;

    private String password;

    public AuthorizationRequest() {
    }
}
