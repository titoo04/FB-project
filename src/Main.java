import java.io.*;
import java.util.*;


public class Main {
    static Scanner input = new Scanner(System.in);
    public static int ctr = 0;
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<User> usersToShow = new ArrayList<>();
    public static ArrayList<Post> posts = new ArrayList<>();

    public static void main(String[] args)
    {
        if (ctr == 0)
        {
            loadUsersFromFile();
        }
        credentialsEntry(args);
    }

    public static void credentialsEntry(String[] args)
    {
        for(Post p:posts){
            System.out.println(p.getPostContent());
        }
        System.out.println("1)Login");
        System.out.println("2)Register");
        String choice = input.next();

        input.nextLine();

        if (choice.equals("1"))
        {
            LogIn loginInstance = new LogIn();
            System.out.println("Enter email: ");
            String email = Main.input.next();
            System.out.println("Enter password: ");
            String password = Main.input.next();
            if (loginInstance.login(email, password)) {
                mainMenu(args);
            } else {
                loginInstance.failed();
            }
        } else if (choice.equals("2"))
        {
            Register register = new Register();
            register.registerUser();
            ctr++;
            credentialsEntry(args);
        } else {
            System.out.println("invalid input pls enter again");
            ctr++;
            credentialsEntry(args);
        }
    }

    public static void mainMenu(String[] args)
    {
        String operationChoice = "y";
        do {
            System.out.println("1.Create Post");
            System.out.println("2.Search for a user");
            System.out.println("3.View feed");
            System.out.println("4.View your profile");
            System.out.println("7.Open your conversations");
            System.out.println("8.Logout");
            operationChoice = input.next();

            Post pInstance = new Post();
            switch (operationChoice)
            {
                case "1":
                    // create post
                    pInstance.createPost();
                    break;
                case "2":
                    System.out.println("Enter username you want to search for");
                    String Usernametosearch = input.next();
                    User.userSearch(Usernametosearch);
                    System.out.println("Choose the user who you want to send a friend request");
                    int index = input.nextInt();
                    User.sendRequest(index);
                    break;
                case "3":
                    if (LogIn.loggedIn.getFeed().isEmpty())
                    {
                        pInstance.noFriendsPosts();
                    } else
                    {
                        pInstance.viewPosts(LogIn.loggedIn.getFeed());
                    }
                    break;
                case "4":
                    Profile profile = new Profile();
                    profile.viewProfile(LogIn.loggedIn);
                    if (LogIn.loggedIn.getPostsCreated().isEmpty())
                    {
                        pInstance.noProfilePosts();
                    } else
                    {
                        pInstance.viewProfilePosts(LogIn.loggedIn.getPostsCreated());
                    }
                    break;
                case "5":
                    //
                    break;
                case "6":
                    boolean addfriend;
                    User.seeRequests(LogIn.loggedIn);
                    if (User.seeRequests(LogIn.loggedIn))
                        addfriend = true;
                    else
                        addfriend = false;
                    break;
                case "7":
                    Conversation conversation = new Conversation();
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
            System.out.println("do you want to do another operation? (y/n)");
            operationChoice = input.next();
        } while (operationChoice.equals("y"));
    }

    private static void loadUsersFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] userInfo = line.split(" ");
                int id = Integer.parseInt(userInfo[0]);
                String name = userInfo[1];
                String[] userDetails = reader.readLine().split(" ");

                try
                {
                    RegisteredUser user = new RegisteredUser(
                            id,
                            name,
                            userDetails[0],
                            userDetails[1],
                            userDetails[2],
                            userDetails[3]
                    );
                    users.add(user);
                } catch (Exception e)
                {
                    throw new HandlingExceptions("reading", "users.txt", e);
                }
            }
        }
        catch (IOException | HandlingExceptions e)
        {
            e.printStackTrace();
        }
    }

    /*private static void loadPosts() {
        Scanner fileScanner = new Scanner(new File("posts.txt"));

        Post currentPost = null; // Start with no current post
        Comment currentComment = null;
        String line;

        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();

            if (line.isEmpty()) {
                continue; // Skip empty lines
            }

            if (line.startsWith("---")) { // new post
                if (currentPost != null) {
                    posts.add(currentPost); // Add the current post to the list before starting a new one
                }
                currentPost = new Post(); // Initialize a new post
                currentComment = null; // Reset current comment for the new post
            } else if (currentPost != null) { // Make sure we have a current post
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    switch (key) {
                        case "userID":
                            currentPost.setUserId(Integer.parseInt(value));
                            break;
                        case "content":
                            currentPost.setPostContent(value);
                            break;
                        case "privacyOption":
                            currentPost.setPrivacyOptions(Integer.parseInt(value));
                            break;
                        case "reacts":
                            currentPost.setReacts(Integer.parseInt(value));
                            break;
                        case "reacted":
                            currentPost.setReacted(Boolean.parseBoolean(value));
                            break;
                        case "taggedUsers":
                            String[] taggedUserIDs = value.split("\\s+"); // Extract user IDs
                            for (String userID : taggedUserIDs) {
                                int userIndex = Integer.parseInt(userID) - 1; // Convert ID to index (assuming IDs start from 1)
                                if (userIndex >= 0 && userIndex < users.size()) { // Check for valid index
                                    currentPost.taggedUsers.add(users.get(userIndex)); // Add user object from user list
                                } else {
                                    // Handle invalid user ID (optional: log error, skip invalid ID)
                                }
                            }
                            break;
                        default:
                            // Handle unsupported key (optional)
                    }
                } else if (line.startsWith("  ")) { // Comment
                    if (currentComment != null) {
                        currentPost.comments.add(currentComment); // Add the previous comment to the current post
                    }
                    String[] commentParts = line.trim().split(":");
                    if (commentParts.length == 2) {
                        currentComment = new Comment(Integer.parseInt(commentParts[0].trim()), commentParts[1].trim());
                    }
                } else if (currentComment != null && line.startsWith("    ")) { // Reply
                    String replyContent = line.trim();
                    if (replyContent.startsWith("reacts:")) {
                        currentComment.replies.get(currentComment.replies.size() - 1).setValue(Integer.parseInt(replyContent.split(":")[1].trim()));
                    } else {
                        currentComment.replies.add(new Pair<>(replyContent, 0));
                    }
                }
            }
        }

        // Add the last post if it exists
        if (currentPost != null && currentPost.getPostContent() != null) {
            posts.add(currentPost);
        }

        fileScanner.close();
    }

    public static void writePosts ()
    {
        FileWriter fileWriter = new FileWriter("posts.txt");
        PrintWriter writer = new PrintWriter(fileWriter);

        for (Post post : posts)
        {
            writer.println(post.getID());

            writer.println("userID: " + post.getUserId());
            writer.println("content: " + post.getPostContent());
            writer.println("privacyOption: " + post.getPrivacyOptions());
            writer.println("reacts: " + post.getReacts());
            writer.println("reacted: " + post.isReacted());

            for (Comment comment : post.comments)
            {
                writer.println("  " + comment.getUserID() + ": " + comment.getContent());
                for (Pair<String, Integer> reply : comment.replies)
                {
                    writer.println("    " + reply.getKey());
                    writer.println("      reacts: " + reply.getValue());
                }
            }

            // Write tagged users by their ID (index + 1)
            writer.println("taggedUsers:");
            for (User tu : post.taggedUsers)
            {
                writer.println("  " + (tu.getID())); // Assuming user IDs start from 1
            }

            writer.println("-----");
        }

        writer.close();
    }*/
}
