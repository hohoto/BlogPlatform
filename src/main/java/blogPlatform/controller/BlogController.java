package blogPlatform.controller;

import blogPlatform.model.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Text;
import java.util.List;

@Controller
public class BlogController {
    @GetMapping("/blog")
    @ResponseBody
    public List<Blog> blogList(){
        return null;
    }

    @GetMapping("/blog/:blogId")
    @ResponseBody
    public Blog getBlogById(@PathVariable Integer blogId){
        return null;
    }

    @PostMapping("/blog")
    @ResponseBody
    public Object createBlog(@RequestBody Text text){
        return null;
    }

    @PutMapping("/blog/:blogId")
    @ResponseBody
    public Object updateBlog(@RequestBody Text text){
        return null;
    }

    @DeleteMapping("/blog/:blogId")
    @ResponseBody
    public Object deleteBlog(){
        return null;
    }
}
