package J1.Model;

public class UserValidator {
    private boolean userValidator;
    String usernameValidator;
    String passwordValidator;

    public boolean usernameValidator(String usernameValidator){
        if (usernameValidator==null){
            return false;
        }
        if (usernameValidator.length()<1||usernameValidator.length()>15){
            return false;
        }
        return true;
    }

    public boolean passwordValidator(String passwordValidator){
        if (passwordValidator==null){
            return false;
        }
        if (passwordValidator.length()<6||passwordValidator.length()>16){
            return false;
        }
        return true;
    }
}
