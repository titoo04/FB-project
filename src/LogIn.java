import java.io.*;
import java.util.*;

public class LogIn extends User
{
    static ArrayList<User> Users= new ArrayList<>();
    boolean Validation = false;
    public LogIn()
    {
     loadUsersFromFile();
     Validate();
     //enter profile
    }

    public void Validate(){
        this.email = Register.input.next() ;
        this.passWord = Register.input.next();
        for (User x:Users)
        {
            if(email.equals(x.email) && passWord.equals(x.passWord))
            {
                Welcome w= new Welcome(x);
                break;
            }

        }
    }

    private void loadUsersFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");

                User user = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
                Users.add(user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
