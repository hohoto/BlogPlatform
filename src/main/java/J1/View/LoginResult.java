package J1.View;

public class LoginResult {
        private String status;
        private boolean isLogin;
        private String avatar;
        private Object data;


        public LoginResult(String status,boolean isLogin, String avatar,Object data) {
            this.status = status;
            this.isLogin = isLogin;
            this.avatar = avatar;
            this.data = data;
        }

    public String getStatus() {
        return status;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public String getAvatar() {
        return avatar;
    }

    public Object getData() {
        return data;
    }

}


