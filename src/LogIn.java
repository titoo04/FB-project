import java.util.Scanner;
public class LogIn extends User
{
    public LogIn(String userName, String passWord)
    {
        Scanner input = new Scanner(System.in);
        this.email =input.next() ;
        this.passWord =input.next();
    }
    public boolean userValidation()
    {
        return true;
    }
}
