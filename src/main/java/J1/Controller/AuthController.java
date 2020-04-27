package J1.Controller;


import J1.View.LoginResult;
import J1.Model.User;
import J1.UserService;
import J1.View.StatusChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Inject
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/auth")
    @ResponseBody
    public Object auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication==null?null:authentication.getName();
        User loggedInUser = userService.getUserByUsername(username);
        if (username == null) {
            return StatusChecker.success("用户没有登录",null);
        }
        return new StatusChecker("ok", null, true, userService.getUserByUsername(username));
    }

    @PostMapping("/auth/register")
    @ResponseBody
    public Object register(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        if (username == "" || password == "") {
            return StatusChecker.failure("用户名或密码不正确");
        }
        if (username.length() < 1 || username.length() > 15) {
            return StatusChecker.failure("用户名长度需要为1-15位");
        }
        if (password.length() < 6 || password.length() > 16) {
            return StatusChecker.failure("密码长度需要为6-16位");
        }
        User user = userService.getUserByUsername(username);
        if (user == null) {
            userService.save(username, password);
            return StatusChecker.success("注册成功", userService.getUserByUsername(username));
        }
        return StatusChecker.failure("用户名已存在");
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody Map<String, Object> usernameAndPassword, HttpServletRequest request) {
        String username = usernameAndPassword.get("username").toString();
        String password = usernameAndPassword.get("password").toString();
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return StatusChecker.failure("用户不存在");
        }
        System.out.println(userDetails.getAuthorities());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            User loggedInUser = userService.getUserByUsername(username);
            return StatusChecker.success("登录成功", loggedInUser);
        } catch (BadCredentialsException e) {
            return StatusChecker.failure("密码不正确");
        }
    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Object logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.getUserByUsername(username);
        if (username == null) {
            return StatusChecker.failure("用户尚未登录");
        }
        SecurityContextHolder.clearContext();
        return StatusChecker.success("注销成功", false);
    }
}





