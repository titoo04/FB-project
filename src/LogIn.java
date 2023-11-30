import com.sun.security.jgss.GSSUtil;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class LogIn extends User
{
    static ArrayList<User> Users= new ArrayList<>();
    boolean Validation = false;
    public LogIn()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");

                User user = new User(userInfo[0],userInfo[1],userInfo[2],userInfo[3],userInfo[4]);
                Users.add(user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        do
        {
            System.out.println("Please Enter your email");
            Scanner input = new Scanner(System.in);
            this.email = input.next() ;
            System.out.println("Please Enter your password");
            this.passWord = input.next();
            for (User x:Users)
            {
                if(email.equals(x.email) && passWord.equals(x.passWord))
                {
                    System.out.println("Welcome page will appear now");
                    // welcome page
                    Validation = true;
                    break;
                }
                else
                {
                    Validation = false;
                }
            }
            if (!Validation)
            {
                System.out.println("You entered an incorrect email or password..");
                System.out.println("Please enter the correct email and password");
            }

        }
        while(!Validation);
    }
    public static void displayusers()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");

                User user = new User(userInfo[0],userInfo[1],userInfo[2],userInfo[3],userInfo[4]);
                Users.add(user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        for(User x :Users)
        {
            System.out.println(x.userName+' '+x.passWord+' '+x.email+' '+x.gender+ ' '+x.birthDate);
        }
    }

}
