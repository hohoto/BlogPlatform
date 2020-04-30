package J1.View;

import J1.Model.Blog;

import java.util.List;

public class BlogResult extends Result<Blog> {
    public BlogResult(String status, String msg, Blog data) {
        super(status, msg, data);
    }

    public BlogResult(String status, String msg) {
        super(status, msg, null);
    }

    public static Object sucess(Blog data) {
        return new BlogResult("ok", "获取成功", data);
    }

    public static Object failure() {
        return new BlogResult("fail", "系统异常");
    }

}
