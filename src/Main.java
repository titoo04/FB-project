import java.io.*;
import java.util.*;
import java.util.regex.*;


public class Main
{
    static Scanner input = new Scanner(System.in);
    public static int ctr = 0;
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<User> usersToshow= new ArrayList<>();
    public static void main(String[] args)
    {

        if(ctr == 0) loadUsersFromFile();
        credentialsEntry(args);

    }
    public static void credentialsEntry(String [] args)
    {
        System.out.println("1)Login");
        System.out.println("2)Register");
        String choice = input.next();

        if(choice.equals("1"))
        {
            System.out.println("Enter email: ");
            String email = Main.input.next();
            System.out.println("Enter password: ");
            String password = Main.input.next();
            if(LogIn.login(email, password))
            {
                mainMenu(args);
            }
        }
        else if(choice.equals("2"))
        {
            Register.registerUser();
            ctr++;
            credentialsEntry(args);
        }
        else
        {
            System.out.println("invalid input pls enter again");
            ctr++;
            credentialsEntry(args);
        }
    }
    public static void mainMenu(String [] args)
    {
        String operationChoice="y";
        do
        {
            System.out.println("1.Create Post");
            System.out.println("2.Search for a user");
            System.out.println("3.View feed");
            System.out.println("4.View your profile");
            System.out.println("7.Open your conversations");
            System.out.println("8.Logout");
            operationChoice=input.next();
            switch(operationChoice)
            {
                case "1":
                    // create post
                    Post.createPost();
                    operationChoice=input.next();
                    break;
                case "2":
                    System.out.println("Enter username you want to search for");
                    String Usernametosearch=input.next();
                    User.userSearch(Usernametosearch);
                    System.out.println("Choose the user who you want to send a friend request");
                    int index = input.nextInt();
                    User.sendRequest(index);
                    break;
                case "3":
                    if(LogIn.loggedIn.feed.isEmpty())
                    {
                        Post.noFriendsPosts();
                    }
                    else
                    {
                        Post.viewPosts(LogIn.loggedIn.feed);
                    }
                    System.out.println("Do you want to do another operation ? (y/n)");
                    operationChoice=input.next();
                    break;
                case "4":
                    Profile profile=new Profile();
                    profile.viewProfile(LogIn.loggedIn);
                    profile.viewPosts(LogIn.loggedIn);
                    System.out.println("Do you want to do another operation ? (y/n)");
                    operationChoice=input.next();
                    break;
                case "5":
                    //
                    System.out.println("Do you want to do another operation ? (y/n)");
                    operationChoice=input.next();
                    break;
                case "6":
                    boolean addfriend;
                    User.seeRequests(LogIn.loggedIn);
                    if(User.seeRequests(LogIn.loggedIn))
                        addfriend = true;
                    else
                        addfriend = false;
                    break;
                case "7":
                    Conversation conversation= new Conversation();
                    conversation.LoadConversationsFromFile(Main.users);
                    conversation.DisplayConvos(LogIn.loggedIn);
                    conversation.writeConversationsInFile(Main.users);
                    break;
                case "8":
                    credentialsEntry(args);
                    break;
                default:
                    System.out.println("Invalid Selection Please check your selection");
                    mainMenu(args);
            }
            System.out.println("do you want to do another operation?");
            operationChoice=input.next();
        }while(operationChoice.equals("y"));
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