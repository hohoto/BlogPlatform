package J1.View;

import J1.Model.Blog;

import java.util.List;

public class BlogListResult extends Result<List<Blog>>{
    private Integer total;
    private Integer page;
    private Integer totalPage;

    public BlogListResult(String status, String msg, List<Blog> data, Integer total, Integer page, Integer totalPage) {
        super(status, msg, data);
        this.total = total;
        this.page = page;
        this.totalPage = totalPage;
    }

    public BlogListResult(String status, String msg) {
        super(status, msg, null);
        this.total = null;
        this.page = null;
        this.totalPage = null;
    }

    public static Object sucess(List<Blog> data,Integer total, Integer page,Integer totalpage){
        return new BlogListResult("ok","获取成功",data,total,page,totalpage);
    }

    public static Object failure(){
        return  new BlogListResult("fail","系统异常");
    }
}
