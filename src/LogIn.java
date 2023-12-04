import java.io.*;
import java.util.*;

public class LogIn {
    public LogIn() {
    }

    public static void login(String email, String password) {

        //enter profile
        Welcome welcome = new Welcome(Validate(email, password));
    }

    public static User Validate(String email, String password)
    {
        for (User u : Main.users)
        {
            if (email.equals(u.getEmail()) && password.equals(u.getPassWord()))
            {
                return u;
            }

        }
        return null;
    }

}
