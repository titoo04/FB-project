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

        if(ctr == 0) loadDataFromFile();
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
            else
            {
                LogIn.failed();
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
                    System.out.println("What's on your mind");
                    String postContent = Main.input.nextLine();
                    //makeTags();
                    int privacyNum = Post.privacy();
                    Post post = new Post(postContent, privacyNum);
                    //,tags );
                    post.createPost(post);
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
                    break;
                case "4":
                    Profile profile=new Profile();
                    profile.viewProfile(LogIn.loggedIn);
                    if(LogIn.loggedIn.postsCreated.isEmpty())
                    {
                        Post.noProfilePosts();
                    }
                    else
                    {
                        Post.viewProfilePosts(LogIn.loggedIn.postsCreated);
                    }
                    break;
                case "5":
                    //
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
            System.out.println("do you want to do another operation? (y/n)");
            operationChoice=input.next();
        }while(operationChoice.equals("y"));
    }

    private static void loadDataFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");

                RegisteredUser user = new RegisteredUser
                        (
                        Integer.parseInt(userInfo[0]),
                        userInfo[1],
                        userInfo[2],
                        userInfo[3],
                        userInfo[4],
                        userInfo[5]
                );
                users.add(user);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
            try (BufferedReader reader = new BufferedReader(new FileReader("conversations.txt")))
            {
                String line;
                while ((line= reader.readLine())!=null)
                {
                    Conversation conversation= new Conversation();
                    int conversationId = Integer.parseInt(line.substring("Conversation Id: ".length()));
                    line= reader.readLine();
                    if (line.startsWith("Participants:"))
                    {
                        // Extract names dynamically using a list
                        conversation.setId(conversationId);
                        // Split line after "Participants:" and trim whitespace
                        String participantsList = line.substring(line.indexOf(":") + 2).trim();
                        // Loop until no more names are found
                        boolean hasNextName = true;
                        while (hasNextName)
                        {
                            // Find the next comma or end of string
                            int commaIndex = participantsList.indexOf(",");
                            int endIndex = commaIndex == -1 ? participantsList.length() : commaIndex;

                            // Extract the current name and trim whitespace
                            String userName = participantsList.substring(0,endIndex).trim();
                            for (User participant:Main.users)
                            {
                                if (participant.getUserName().equals(userName))
                                {
                                    conversation.participants.add(participant);
                                    participant.convos.add(conversation);
                                    break;
                                }
                            }
                            // Update participantsList for next iteration
                            if (commaIndex != -1)
                            {
                                participantsList = participantsList.substring(endIndex + 1).trim();
                            }
                            else
                            {
                                hasNextName = false;
                            }
                        }
                        // Stop searching after finding participants list
                    }
                    int chatSize = Integer.parseInt(reader.readLine());
                    for (int k = 0; k < chatSize; k++)
                    {
                        conversation.chat.add(reader.readLine());
                    }
                }
            }

            catch (IOException e)
         {
            e.printStackTrace();
        }
    }
}