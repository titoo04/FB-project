import java.io.*;
import java.util.*;

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
        if(Validate()){
            //enter profile
            System.out.println("Works");
        }else {
            //1-try again   2-register  3-forgot info
            System.out.println("dead");
        }
    }

    public boolean Validate(){
        Scanner input = new Scanner(System.in);
        this.email = input.next() ;
        this.passWord = input.next();
        for (User x:Users)
        {
            if(email.equals(x.email) && passWord.equals(x.passWord))
            {
                // push sub branch
                Validation = true;
                Welcome w= new Welcome(x);
                break;
            }

        }
        return Validation;
    }

    public static void displayUsers()
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
            System.out.println(x.userName+' '+x.email+' '+x.passWord+' '+x.gender+ ' '+x.birthDate);//and here
        }
    }

}