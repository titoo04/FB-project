import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Main
{
    public static ArrayList<User> usersToshow= new ArrayList<>();
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

            if(LogIn.login(email,password));
            {
                String signinChoice="y";
                do
                {
                    System.out.println("1.Create Post");
                    System.out.println("2.Search for a friend");
                    System.out.println("3.See friend's posts");
                    System.out.println("4.View your profile");
                    System.out.println("5.View feed");
                    int operationChoice=input.nextInt();
                    switch(operationChoice)
                    {
                        case 1:
                            // create post
                            Post.createPost();
                            signinChoice=input.next();
                            break;
                        case 2:
                            System.out.println("Enter username you want to search for");
                            String Usernametosearch=input.next();
                            User.userSearch(Usernametosearch);
                            System.out.println("Choose the user who you want to send a friend request");
                            int index = input.nextInt();
                            User.sendRequest(index);
                            break;
                        case 3:
                            //
                            break;
                        case 4:
                            Profile profile=new Profile();
                            profile.viewProfile(LogIn.loggedIn);
                            profile.viewPosts(LogIn.loggedIn);
                            System.out.println("Do you want to do another operation ? (y/n)");
                            signinChoice=input.next();
                            break;
                        case 5:
                            //view feed
                            signinChoice=input.next();
                            break;
                            //  case 6:
                            //  if(User.seeRequests(LogIn.loggedIn))
                            //  System.out.println(1);
                            //  else System.out.println(0);
                    }
                }while(signinChoice.equals("y"));
            }

        }else if(choice.equals("2")){
            Register.registerUser();
            main(args);
        }//else if(choice.eguals("3")){
        //    return ;
        //}
        else {
            System.out.println("invalid input pls enter again");
            main(args);
        }
    }
    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
