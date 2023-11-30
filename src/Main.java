import java.util.*;
import java.util.regex.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("1)Login");
        System.out.println("2)Register");
        String ch = input.next();

        if(ch.equals("1")){
            LogIn l = new LogIn();
        }else if(ch.equals("2")){
            Register r = new Register();
        }else {
            System.out.println("invalid input pls enter again");
            main(args);
        }
    }

}
