package J1.Controller;

import J1.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    private MockMvc mvc;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new AuthController(authenticationManager,userService)).build();
    }

    @Test
    void returnNotLoginByDefault() throws Exception {
        mvc.perform(get("/auth")).andExpect(mvcResult ->
                Assertions.assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("用户没有登录")));
    }

    @Test
    void testlogin() throws Exception {
        Map<String,String> usernameAndPassword = new ConcurrentHashMap<>();
        usernameAndPassword.put("username","testuser");
        usernameAndPassword.put("password",bCryptPasswordEncoder.encode("encodedtesting"));
        Mockito.when(userService.loadUserByUsername("testuser")).thenReturn(new User("testuser","encodedtesting", Collections.emptyList()));
        Mockito.when(userService.getUserByUsername("testuser")).thenReturn(new J1.Model.User(1,"testuser","encodedtesting"));
        MvcResult response = mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(new ObjectMapper().writeValueAsString(usernameAndPassword))).andExpect(status().isOk()).
                andExpect(mvcResult ->
                        Assertions.assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("true"))).andReturn();

        HttpSession session = response.getRequest().getSession();
        System.out.println();
        mvc.perform(get("/auth").session((MockHttpSession)session)).andExpect(mvcResult ->
                Assertions.assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("testuser")));
    }
}