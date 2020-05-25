package avalith.votingAPI.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final HttpStatus statusErrorResponse;

    public JWTAuthenticationFailureHandler(HttpStatus statusErrorResponse) {
        this.statusErrorResponse = statusErrorResponse;
    }

    public JWTAuthenticationFailureHandler() {
        this.statusErrorResponse = HttpStatus.UNAUTHORIZED;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setStatus(statusErrorResponse.value());
        response.setContentType("application/json");
        response.getWriter().append(jsonResponse());
    }

    private String jsonResponse() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
                + "\"status\": " + statusErrorResponse.value() + ", "
                + "\"error\": \"Unauthorized\", "
                + "\"message\": \"Authentication failed: bad credentials\", "
                + "\"path\": \"/login\"}";
    }
}

