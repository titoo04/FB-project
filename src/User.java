import  java.util.*;

public abstract class User
{
    private int ID;
    private String name;
    private String email;
    private String passWord;
    private String gender;
    private String birthDate;
    public ArrayList<User>usersToshow= new ArrayList<>();
    public ArrayList<Post> postsCreated = new ArrayList<>();
    public static ArrayList<Post> feed = new ArrayList<>();
    public  ArrayList<User> pendingRequests = new ArrayList<>();
    public  ArrayList<User> friends = new ArrayList<>();
    public  ArrayList<User> restrictedFriends = new ArrayList<>();
    public  ArrayList<User> regularFriends = new ArrayList<>();
    public ArrayList<Conversation> convos = new ArrayList<>();

    public User(int ID, String name, String email, String passWord, String gender, String birthDate)
    {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.passWord = passWord;
        this.gender = gender;
        this.birthDate = birthDate;
    }
    public User()
    {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void userSearch(String searchUsername)
    {
        RegisteredUser loggedInUser =  new RegisteredUser();
        for(User u:Main.users)
        {
            boolean found = false;
            if (searchUsername.equals(u.getName()))
            {
                found = true;
            }
            if (found)
            {
                usersToshow.add(u);
            }
        }
        for (int i = 0; i < usersToshow.size(); i++)
        {
            // User currentUser = Main.usersToshow.get(i);
            System.out.println((i + 1) + " " + usersToshow.get(i).getName() + " " + usersToshow.get(i).getEmail());
            // Find mutual friends
            ArrayList<User> mutualFriends=new ArrayList<>();
            findMutualFriends(usersToshow.get(i),mutualFriends);
            if (!mutualFriends.isEmpty()) {
                System.out.print("Mutual Friends: ");
                for (User friend : mutualFriends) {
                    System.out.print(friend.getName() + " ");
                }
                System.out.println();
            }
        }
    }
    private  void findMutualFriends(User user,ArrayList<User>mutualFriends)
    {
        for(User loggedInUserFriend : LogIn.loggedIn.friends)
        {
            if(user.friends.contains(loggedInUserFriend))
            {
                mutualFriends.add(loggedInUserFriend);
            }
        }
    }

    public void sendRequest(int index)
    {
        int userIndex = index - 1;
        User user = usersToshow.get(userIndex);
        String choice;
        if (LogIn.loggedIn.friends.contains(user)) {
            System.out.println("Press 1) to restrict friend");
            System.out.println("Press 2) to send a message");
            choice = Main.input.next();
            switch (choice) {
                case "1":
                    break;
                case "2":
                    Conversation newConversation = new Conversation();
                    newConversation.chat.add("Chat is empty");
                    newConversation.participants.add(user);
                    newConversation.participants.add(LogIn.loggedIn);
                    LogIn.loggedIn.convos.add(newConversation);
                    user.convos.add(newConversation);
                    System.out.println("Add participant: ");
                    break;
            }
        } else {
            System.out.println("Press 1) Send Friend request");
            choice = Main.input.next();
            switch (choice) {
                case "1":
                    usersToshow.get(userIndex).pendingRequests.add(LogIn.loggedIn);
                    break;
                case "2":

                    break;
            }
        }
    }
    public static User seeRequests(User u)
    {
        if (u.pendingRequests.isEmpty()) {
            System.out.println("You don't have any pending requests.");
            return null;
        }

        for (int i = 0; i < u.pendingRequests.size(); i++) {

            System.out.println((i + 1) + " " +u.pendingRequests.get(i).getName() + " " + u.pendingRequests.get(i).getEmail());
        }

        System.out.println("Choose the user you want to select:");
        int choice = Main.input.nextInt();

        if (choice < 1 || choice > u.pendingRequests.size()) {
            System.out.println("Invalid choice. Please choose a valid user.");
            return null;
        }

        int index = choice - 1;

        System.out.println("1 - Confirm request");
        System.out.println("2 - Decline request");

        int actionChoice = Main.input.nextInt();

        User selectedUser = u.pendingRequests.get(index);
        if (actionChoice == 1)
        {
            // Confirmation logic
            System.out.println("Confirmed request from " + selectedUser.getName());
            u.pendingRequests.remove(index);  // Remove from pending requests after confirmation
            return selectedUser;
        } else if (actionChoice == 2)
        {
            // Decline logic
            System.out.println("Declined request from " + selectedUser.getName());
            u.pendingRequests.remove(index);  // Remove from pending requests after decline
            return null;
        } else {
            System.out.println("Invalid choice. No action taken.");
            return null;
        }
    }
    static void addFriend(User friend)
    {
        if(!LogIn.loggedIn.friends.contains(friend))
        {
            LogIn.loggedIn.friends.add(friend);
            friend.friends.add(LogIn.loggedIn);
            System.out.println("1. Add as a restricted friend (Can see the public posts only)");
            System.out.println("2. Add as a regular friend(Can see all posts)");
            int friendChoice = Main.input.nextInt();
            switch (friendChoice)
            {
                case 1:
                    LogIn.loggedIn.restrictedFriends.add(friend);
                    friend.restrictedFriends.add(LogIn.loggedIn);
                    System.out.println("Added as restricted friend successfully !");
                    break;
                case 2:
                    LogIn.loggedIn.regularFriends.add(friend);
                    friend.regularFriends.add(LogIn.loggedIn);
                    System.out.println("Added as regular friend successfully !");
                    break;
                default:
                    System.out.println("Invalid choice. No friend added.");
            }
        }
        else
            System.out.println(LogIn.loggedIn.getName()+ " is already friends with" + friend.getName());
    }
}