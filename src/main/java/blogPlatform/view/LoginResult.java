package blogPlatform.view;

import blogPlatform.model.User;

public class LoginResult extends Result<User> {
        private boolean isLogin;

        public LoginResult(String status, boolean isLogin) {
            this(status, null, isLogin, null);
        }

        public LoginResult(String status, String msg, boolean isLogin) {
            this(status, msg, isLogin, null);
        }

        public LoginResult(String status, String msg, boolean isLogin, User data) {
            super(status,msg,data);
            this.isLogin = isLogin;
        }

        public static LoginResult success(String msg) {
            return new LoginResult("ok", msg, true, null);
        }

        public static LoginResult success(String msg, boolean isLogin) {
            return new LoginResult("ok", msg, isLogin, null);
        }

        public static LoginResult success(String msg, User data) {
            return new LoginResult("ok", msg, true, data);
        }

        public static LoginResult failure(String msg) {
            return new LoginResult("fail", msg, false, null);
        }

        public boolean isLogin() {
            return isLogin;
        }

    }



