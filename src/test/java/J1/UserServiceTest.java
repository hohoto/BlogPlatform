package J1;

import J1.Model.Dao.UserMapper;
import J1.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserMapper mockMapper;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void testSave(){
        Mockito.when(mockBCryptPasswordEncoder.encode("testing")).thenReturn("encodedtesting");
        userService.save("testuser","testing");
        Mockito.verify(mockMapper).saveUserByUsername("testuser","encodedtesting");
    }

    @Test
    public void testGetUserByUsername(){
        userService.getUserByUsername("testuser");
        Mockito.verify(mockMapper).findUserByUsername("testuser");
    }

    @Test
    public void throwUsernameNotFoundException(){
        Mockito.when(mockMapper.findUserByUsername("testuser")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()->userService.loadUserByUsername("testuser"));
    }

    @Test
    public void returnUserWhenFound(){
        Mockito.when(mockMapper.findUserByUsername("testuser"))
        .thenReturn(new User(1,"testuser","encodedtesting"));
        UserDetails userDetails = userService.loadUserByUsername("testuser");
        Assertions.assertEquals("testuser",userDetails.getUsername());
        Assertions.assertEquals("encodedtesting",userDetails.getPassword());
    }
}