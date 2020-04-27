package J1.View;

import J1.Model.User;

public class StatusChecker {
        private String status;
        private String msg;
        private boolean isLogin;
        private static Object data;

        public StatusChecker(String status, boolean isLogin) {
            this(status, null, isLogin, null);
        }

        public StatusChecker(String status, String msg, boolean isLogin) {
            this(status, msg, isLogin, null);
        }

        public StatusChecker(String status, String msg, boolean isLogin, Object data) {
            this.status = status;
            this.msg = msg;
            this.isLogin = isLogin;
            this.data = data;
        }

        public static StatusChecker success(String msg) {
            return new StatusChecker("ok", msg, true, null);
        }

        public static StatusChecker success(String msg, boolean isLogin) {
            return new StatusChecker("ok", msg, isLogin, null);
        }

        public static StatusChecker success(String msg, Object data) {
            return new StatusChecker("ok", msg, true, data);
        }

        public static StatusChecker failure(String msg) {
            return new StatusChecker("fail", msg, false, null);
        }

        public boolean isLogin() {
            return isLogin;
        }

        public String getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public Object getData() {
            return data;
        }
    }



