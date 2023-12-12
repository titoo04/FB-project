import java.io.*;
import java.util.*;

public class LogIn {
    public LogIn()
    {
    }

    public static void login(String email, String password) {

        //enter profile
        Validate(email, password);

    }

    public static Boolean Validate (String email, String password)
    {
        for (User u : Main.users)
        {
            if (email.equals(u.getEmail()) && password.equals(u.getPassWord()))
            {
             Welcome w= new Welcome(u);
            }
        }
        return false;
    }

}
