package blogPlatform.view;

public class Result<T> {
        private String status;
        private String msg;
        private T data;


        public Result(String status, String msg, T data) {
            this.status = status;
            this.msg = msg;
            this.data = data;
        }

    public String getStatus() {
        return status;
    }

    public String getAvatar() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}


