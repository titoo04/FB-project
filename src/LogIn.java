import java.io.*;
import java.util.*;

public class LogIn
{
    public static User loggedIn;
    public LogIn()
    {}

    public static boolean login(String email, String password){
        if(checkValidate(email, password))
        {
            return true;
        }
        else
        {
            return false;
            // failed();
        }
    }

    private static void failed()
    {
        System.out.println("Login failed");
        System.out.println("1)Retry");
        System.out.println("2)Register");
        System.out.println("3)Forgot password?");
        String option = Main.input.next();

        if(option.equals("1"))
        {
            System.out.println("Enter email: ");
            String email=Main.input.next();
            System.out.println("Enter password: ");
            String password = Main.input.next();

            if(checkValidate(email, password))
            {
                Profile profile=new Profile();
                profile.viewProfile(loggedIn);
                //enter profile

            }
            else
            {
                failed();
            }
        }
        else if(option.equals("2"))
        {
            Register.registerUser();
        }
        else if(option.equals("3"))
        {
            forgotPassword();
        }
        else
        {
            System.out.println("Invalid input");
            failed();
        }
    }

    private static void forgotPassword ()
    {
        System.out.println("Please enter your email: ");
        String email = Main.input.next();
        if(Register.userExists(email))
        {
            //setpassword
        }
        else
        {
            checkEmail();
        }
    }

    private static void checkEmail()
    {
        System.out.println("Wrong email");
        System.out.println("1)try again");
        System.out.println("2)cancel");

        String forgotChoice = Main.input.next();

        if(forgotChoice.equals("1"))
        {
            forgotPassword();
        }
        else if (forgotChoice.equals("2"))
        {
            Main.main(new String[]{});
        }
        else
        {
            System.out.println("Invalid input, please enter again");
            checkEmail();
        }
    }

    public static boolean checkValidate(String email, String password)
    {
        for (User u:Main.users)
        {
            if(email.equals(u.getEmail()) && password.equals(u.getPassWord()))
            {
                loggedIn = u;
                return true;
            }

        }
        return false;
    }

}
