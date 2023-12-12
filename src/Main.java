import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main
{
    static Scanner input = new Scanner(System.in);
    public static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args)
    {
        loadUsersFromFile();


        System.out.println("1)Login");
        System.out.println("2)Register");
        String choice = input.next();

        if(choice.equals("1"))
        {
            System.out.println("Enter email: ");
            String email = Main.input.next();
            System.out.println("Enter password: ");
            String password = Main.input.next();

            LogIn.login(email,password);
        }
        else if(choice.equals("2"))
        {
            Register.registerUser();
            main(args);
        }
        //else if(choice.eguals("3")){
        //    return ;
        //}
        else
        {
            System.out.println("invalid input pls enter again");
            main(args);
        }
        //write()
    }

    private static void loadUsersFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");

                RegisteredUser user = new RegisteredUser(
                        Integer.parseInt(userInfo[0]),
                        userInfo[1],
                        userInfo[2],
                        userInfo[3],
                        userInfo[4],
                        userInfo[5]
                );
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
