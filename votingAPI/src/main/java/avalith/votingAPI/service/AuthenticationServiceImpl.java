package avalith.votingAPI.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/***
 * AuthenticationServiceImpl implements all AuthenticationService methods
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    /***
     * getAuthentication returns authentication details for a current session
     * @return
     */
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
