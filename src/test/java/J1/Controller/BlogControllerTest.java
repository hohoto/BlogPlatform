package J1.Controller;

import J1.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BlogControllerTest {
    private MockMvc mvc;
    @Mock
    private BlogService blogService;

    @BeforeEach
    void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new BlogController()).build();
    }

    @Test
    void returnBlogList(){
    }

    @Test
    void returnBlogById(){
    }

    @Test
    void testBlogSaving(){
    }

    @Test
    void testBlogUpdating(){
    }

    @Test
    void testBlogDeleting(){
    }
}