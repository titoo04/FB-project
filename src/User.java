import  java.util.*;

public abstract class User
{
    private int ID;
    private String name;
    private String email;
    private String passWord;
    private String gender;
    private String birthDate;
    public ArrayList<Post> postsCreated = new ArrayList<>();
    public ArrayList<Post> feed = new ArrayList<>();
    public static ArrayList<User> pendingRequests = new ArrayList<>();
    public static ArrayList<Friend> friends = new ArrayList<>();
    public static ArrayList<User> restrictedFriends = new ArrayList<>();
    public static ArrayList<User> regularFriends = new ArrayList<>();
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
    {

    }

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

    public static void userSearch(String searchUsername)
    {
        for(User u:Main.users)
        {
            boolean found = false;
            if (searchUsername.equals(u.getName()))
            {
                found = true;
            }
            if (found)
            {
                Main.usersToshow.add(u);
            }
        }
        for(int i = 0; i < Main.usersToshow.size() ; i++)
        {
            {
                System.out.println((i + 1) + " " + Main.usersToshow.get(i).getName() + " " + Main.usersToshow.get(i).getEmail());
            }

        }
    }
    public static void sendRequest(int index)
    {
        int userIndex = index - 1;
        for(int i =0;i<Main.usersToshow.size();i++)
        {
            if((userIndex) == i)
            {
                User user = Main.usersToshow.get(i);
                user.pendingRequests.add(LogIn.loggedIn);
            }
        }
        System.out.println("Request sent !");
    }
    public static boolean seeRequests(User u) {
        if (u.pendingRequests.isEmpty()) {
            System.out.println("You don't have any pending requests.");
            return null;
        }

        for (int i = 0; i < u.pendingRequests.size(); i++) {
            User requester = u.pendingRequests.get(i);
            System.out.println((i + 1) + " " + requester.getName() + " " + requester.getEmail());
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
            LogIn.loggedIn.friends.add((Friend)friend);
            friend.friends.add((Friend)LogIn.loggedIn);
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