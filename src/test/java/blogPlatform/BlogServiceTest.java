package blogPlatform;

import blogPlatform.model.dao.BlogDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {
    @Mock
    private BlogDao blogDao;
    @InjectMocks
    private BlogService blogService;

    public void testsave(){
        //verify save blog
    }

    public void testEditBlog(){
        //verify edit blog
    }

    public void deleteBlog(){
        //verify delete blog
    }

    @Test
    public void getBlogById(){
    }

    @Test
    public void getBlogList(){
        blogService.getblogs(1,3,true);
        Mockito.verify(blogDao).getblogs(1,3,true);
    }

    @Test
    public void returnFailureWhenException(){
    }
}