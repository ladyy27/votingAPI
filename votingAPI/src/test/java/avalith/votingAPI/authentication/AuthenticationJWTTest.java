package avalith.votingAPI.authentication;

import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.security.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static avalith.votingAPI.security.TokenProvider.generateToken;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationJWTTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authorizeUserTest() throws Exception {
        AuthorizationRequest request = AuthorizationRequest.builder().username("user1").password("password1")
                .build();
        final MvcResult mvcResult = mockMvc.perform(
                post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getHeader(Constants.HEADER_AUTHORIZATION_KEY)).isNotBlank();
    }

    @Test
    public void notAuthorizeUserTest() throws Exception {
        AuthorizationRequest request = AuthorizationRequest.builder().username("user1").password("password2")
                .build();
        mockMvc.perform(
                post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    public void authorizeAdminUserToGetUserInfoTest() throws Exception {
        String token = getTokenTest("user1", "password1");

        mockMvc.perform(
                get("/users/1").header(Constants.HEADER_AUTHORIZATION_KEY, token))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    private String getTokenTest(String userTest, String password1) throws Exception {
        AuthorizationRequest request = AuthorizationRequest.builder().username("user1").password(password1)
                .build();
        final MvcResult mvcResult = mockMvc.perform(
                post("/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        return mvcResult.getResponse().getHeader(Constants.HEADER_AUTHORIZATION_KEY);
    }

    @Test
    public void authorizeUserToGetUserInfoTest() throws Exception {
        String token = getTokenTest("user1", "password1");

        mockMvc.perform(
                get("/users/1").header(Constants.HEADER_AUTHORIZATION_KEY, token))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void authorizeAdminUserToSaveTest() throws Exception {
        String token = getTokenTest("user1", "password1");

        AuthorizationRequest request = AuthorizationRequest.builder().username("user8").password("password8")
                .build();
        mockMvc.perform(
                post("/users").header(Constants.HEADER_AUTHORIZATION_KEY, token).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print()).andExpect(status().isOk());
    }

}
