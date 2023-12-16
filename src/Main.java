import java.io.*;
import java.util.*;
import java.util.regex.*;


public class Main {
    static Scanner input = new Scanner(System.in);
    public static int ctr = 0;
    public static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        if (ctr == 0) loadDataFromFile();
        credentialsEntry(args);

    }

    public static void credentialsEntry(String[] args) {
        System.out.println("1)Login");
        System.out.println("2)Register");
        String choice = input.next();

        input.nextLine();

        if (choice.equals("1")) {
            System.out.println("Enter email: ");
            String email = Main.input.next();
            System.out.println("Enter password: ");
            String password = Main.input.next();
            LogIn logIn = new LogIn();
            if (logIn.login(email, password)) {
                mainMenu(args);
            } else {
                logIn.failed();
            }
        } else if (choice.equals("2")) {
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

    public static void mainMenu(String[] args) {
        boolean end = false;
        String operationChoice = "y";
        do {
            System.out.println("1.Create Post");
            System.out.println("2.Search for a user");
            System.out.println("3.View feed");
            System.out.println("4.View your profile");
            System.out.println("6.View your pending requests");
            System.out.println("7.Open your conversations");
            System.out.println("8.Logout");
            operationChoice = input.next();
            switch (operationChoice) {
                case "1":
                    // create post
                    System.out.println("What's on your mind");
                    input.nextLine();
                    String postContent = Main.input.nextLine();
                    ArrayList<User> TU = new ArrayList<>();
                    TU = Post.makeTags(TU);
                    int privacyNum = Post.privacy();
                    Post post = new Post(postContent, privacyNum, TU);
                    post.createPost(post);
                    break;
                case "2":
                    System.out.println("Enter username you want to search for");
                    String Usernametosearch = input.next();
                    LogIn.loggedIn.userSearch(Usernametosearch);

                    System.out.println("Choose the user who you want to send a friend request");
                    int index = input.nextInt();
                    LogIn.loggedIn.sendRequest(index);
                    break;
                case "3":
                    if (LogIn.loggedIn.feed.isEmpty()) {
                        Post.noFriendsPosts();
                    } else {
                        Post.viewPosts(LogIn.loggedIn.feed);
                    }
                    break;
                case "4":
                    Profile profile = new Profile();
                    profile.viewProfile(LogIn.loggedIn);
                    if (LogIn.loggedIn.postsCreated.isEmpty()) {
                        Post.noProfilePosts();
                    } else {
                        Post.viewProfilePosts(LogIn.loggedIn.postsCreated);
                    }
                    break;
                case "5":
                    //
                    break;
                case "6":
                    User.addFriend(User.seeRequests(LogIn.loggedIn));
                    break;
                case "7":
                    Conversation conversation = new Conversation();
                    conversation.DisplayConvos(LogIn.loggedIn);
                    break;
                case "8":
                    credentialsEntry(args);
                    break;
                case "9": {
                    writeDataInFile();
                    end = true;
                    break;
                }
                default:
                    System.out.println("Invalid Selection Please check your selection");
                    mainMenu(args);
            }
            if (end)
                break;
            System.out.println("do you want to do another operation? (y/n)");
            operationChoice = input.next();
        } while (operationChoice.equals("y"));
    }

    private static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(" ");
                int id = Integer.parseInt(userInfo[0]);
                String name = userInfo[1];
                String[] userDetails = reader.readLine().split(" ");
                RegisteredUser user = new RegisteredUser(id, name, userDetails[0], userDetails[1], userDetails[2], userDetails[3]);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader reader = new BufferedReader(new FileReader("conversations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Conversation conversation = new Conversation();
                int conversationId = Integer.parseInt(line.substring("Conversation Id: ".length()));
                line = reader.readLine();
                if (line.startsWith("Participants:")) {
                    // Extract names dynamically using a list
                    conversation.setId(conversationId);
                    // Split line after "Participants:" and trim whitespace
                    String participantsList = line.substring(line.indexOf(":") + 2).trim();
                    // Loop until no more names are found
                    boolean hasNextName = true;
                    while (hasNextName) {
                        // Find the next comma or end of string
                        int commaIndex = participantsList.indexOf(",");
                        int endIndex = commaIndex == -1 ? participantsList.length() : commaIndex;

                        // Extract the current name and trim whitespace
                        String userName = participantsList.substring(0, endIndex).trim();
                        for (User participant : Main.users) {
                            if (participant.getName().equals(userName)) {
                                conversation.participants.add(participant);
                                participant.convos.add(conversation);
                            }
                        }

                        // Update participantsList for next iteration
                        if (commaIndex != -1) {
                            participantsList = participantsList.substring(endIndex + 1).trim();
                        } else {
                            hasNextName = false;
                        }
                    }
                    // Stop searching after finding participants list
                }
                int chatSize = Integer.parseInt(reader.readLine());
                for (int k = 0; k < chatSize; k++) {
                    conversation.chat.add(reader.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Post.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int userId;
                // Extract the user id from the line
                userId = Integer.parseInt(line.split(":")[1].trim());

                // Read the next line to get the size of the array of posts
                line = br.readLine();
                int postsSize = Integer.parseInt(line.trim());
                for (int i = 0; i < postsSize; i++) {
                    Post post = new Post();
                    line = br.readLine();
                    int postId = Integer.parseInt(line.split(":")[1].trim());
                    post.setId(postId);
                    String postContentLine = br.readLine();
                    if (postContentLine.startsWith("Post Content:")) {
                        // Extract the post content from the line
                        String postContent = postContentLine.substring("Post Content:".length()).trim();
                        post.setPostContent(postContent);
                    }
//                    String taggedUsersLine = br.readLine();
//                        if (taggedUsersLine.startsWith("Tagged Users:"))
//                        {
//                            // Extract the tagged users from the line
//                            String taggedUsersString = taggedUsersLine.substring("Tagged Users:".length()).trim();
//                            List<String> taggedUsers = Arrays.asList(taggedUsersString.split(", "));
//                            for (User user : Main.users) {
//                                for (int k = 0; k < taggedUsers.size(); k++) {
//                                    if (taggedUsers.get(k).equals(user.getName()))
//                                        post.getTaggedUsers().add(user);
//                                }
//                            }
//                        }
                    line = br.readLine();
                    int Privacy = Integer.parseInt(line.split(":")[1].trim());
                    post.setPrivacyOptions(Privacy);
                    String reactsLine = br.readLine();
                    if (reactsLine.startsWith("Reacts:")) {
                        // Extract the number of reacts from the line
                        int reacts = Integer.parseInt(reactsLine.substring("Reacts:".length()).trim());
                        post.setReacts(reacts);
                    }
                    String commentsSizeLine = br.readLine();
                    int commentsSize = Integer.parseInt(commentsSizeLine.trim());
                    for (int j = 0; j < commentsSize; j++) {
                        // Read the comment
                        String comment = br.readLine();
                        Comment commenT = new Comment(comment);
                        String commentReactsLine = br.readLine();
                        int commentReacts = Integer.parseInt(reactsLine.substring("Reacts:".length()).trim());
                        commenT.setReacts(commentReacts);
                        String repliesSizeLine = br.readLine();
                        int repliesSize = Integer.parseInt(repliesSizeLine.trim());
                        for (int k = 0; k < repliesSize; k++) {
                            // Read the reply
                            String reply = br.readLine();
                            String replyReactsLine = br.readLine();
                            if (replyReactsLine.startsWith("Reply Reacts:")) {
                                // Extract the number of reacts on the reply
                                int replyReacts = Integer.parseInt(replyReactsLine.substring("Reply Reacts:".length()).trim());
                                Pair<String, Integer> Reply = new Pair<>(reply, replyReacts);
                                commenT.getReplies().add(Reply);
                            }
                        }
                        post.comments.add(commenT);
                    }
                    for (User user : users) {
                        if (user.getID() == userId)
                            user.postsCreated.add(post);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeDataInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations.txt", false))) {
            ArrayList<Integer> isAlreadyWritten = new ArrayList<>();
            for (User user : users) {
                for (Conversation conversation : user.convos) {
                    if (!isAlreadyWritten.contains(conversation.getId())) {
                        isAlreadyWritten.add(conversation.getId());
                        writer.write("Conversation Id: " + conversation.getId());
                        writer.newLine();
                        writer.write("Participants: ");
                        for (User participant : conversation.participants) {
                            writer.write(participant.getName() + ',');
                        }
                        writer.newLine();
                        writer.write(String.valueOf(conversation.chat.size()));
                        writer.newLine();
                        for (String line : conversation.chat) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                }
            }
            System.out.println("Data has been written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("Post.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (User user : Main.users) {
                if (!user.postsCreated.isEmpty()) {
                    bufferedWriter.write("User Id:" + user.getID());
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(user.postsCreated.size()));
                    bufferedWriter.newLine();
                    for (Post post : user.postsCreated) {
                        bufferedWriter.write("Post Id:" + post.getID());
                        bufferedWriter.newLine();
                        bufferedWriter.write("Post Content:" + post.postContent);
                        bufferedWriter.newLine();
                        bufferedWriter.write("Privacy option:" + post.privacyOptions);
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.valueOf(post.getReacts()));
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.valueOf(post.comments.size()));
                        for (Comment comment : post.comments) {
                            bufferedWriter.write(comment.getContent() + "\n");
                            bufferedWriter.write("Reacts:" + comment.getReacts() + "\n");

                            for (Pair<String, Integer> reply : comment.getReplies()) {
                                bufferedWriter.write("Reply:" + reply.getKey() + "\n");
                                bufferedWriter.write("Reply Reacts: " + reply.getValue() + "\n");
                            }
                            bufferedWriter.write("\n");
                        }
                    }
                }
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("Friends.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (User user : Main.users)
            {
                if (!user.friends.isEmpty())
                {
                    bufferedWriter.write("User Id:" + user.getID() + "/n");
                    bufferedWriter.write(String.valueOf(user.restrictedFriends.size()));
                    bufferedWriter.write("Restricted Friends:");
                    for (User restrictedFriend : user.restrictedFriends)
                    {
                        bufferedWriter.write(restrictedFriend.getName() + ',');
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(user.regularFriends.size()));
                    bufferedWriter.write("Regular Friends:");
                    for (User regularFriend : user.regularFriends)
                    {
                        bufferedWriter.write(regularFriend.getName() + ',');
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}