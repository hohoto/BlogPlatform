package blogPlatform;

import blogPlatform.model.Blog;
import blogPlatform.model.dao.BlogDao;

import javax.inject.Inject;
import java.util.List;

public class BlogService {
    private BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getblogs(Integer page, Integer userId, boolean atIndex){
        return blogDao.getblogs(page,userId,atIndex);
    }
}
