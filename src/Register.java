import java.util.*;
import java.io.*;

public class Register extends User
{
    ArrayList<User>Users= new ArrayList<>();

    public Register()
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
        Scanner input = new Scanner(System.in);
        this.userName =input.next() ;
        this.email =input.next() ;
        this.passWord =input.next() ;
        this.gender  =input.next() ;
        this.birthDate =input.next() ;
        User user = new User(userName, email, passWord, gender, birthDate);
        Users.add(user);
        System.out.println("User made successfully");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true)))
        {
            for (User x:Users)
            {
                writer.write(x.userName+' '+x.passWord+' '+x.email+' '+x.gender+ ' '+x.birthDate);
                writer.newLine(); // Add a newline for each entry
            }
        System.out.println("Data has been written to file successfully.");
        }
        catch (IOException e)
        {
        e.printStackTrace();
        }
    }
}

