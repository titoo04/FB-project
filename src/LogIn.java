import java.io.*;
import java.util.*;

public class LogIn
{
    public LogIn()
    {}

    public static void login(String email, String password){
        if(Validate(email, password))
        {
            //enter profile
        }
        else
        {
            //1-retry   2-register   3-forgot info
        }
    }

    public static boolean Validate(String email, String password)
    {
        for (User u:Main.users)
        {
            if(email.equals(u.getEmail()) && password.equals(u.getPassWord()))
            {
                return true;
            }

        }
        return false;
    }

}
