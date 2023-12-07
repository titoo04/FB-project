import  java.util.*;

public abstract class User
{

    private int ID;
    private String userName;
    private String email;
    private String passWord;
    private String gender;
    private String birthDate;
    private ArrayList<Post> feed = new ArrayList<>();
    public static ArrayList<User> pendingRequests = new ArrayList<>();
    public static ArrayList<Post>postsCreated=new ArrayList<>();

    public User(int ID, String userName, String email, String passWord, String gender, String birthDate) {
        this.ID = ID;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        boolean found = false;
        for(User u:Main.users)
        {
            if (searchUsername.equals(u.getUserName()))
            {
                found = true;
            }
            else found = false;
            if (found)
            {
                Main.usersToshow.add(u);
            }
        }
        for(int i = 0; i < Main.usersToshow.size() ; i++)
        {
            User user = Main.usersToshow.get(i);
            System.out.println((i + 1) + " " + user.getUserName() + " " + user.getEmail());
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
                User.pendingRequests.add(user);
            }
        }
        System.out.println("Request sent !");
    }
    public static boolean seeRequests(User u)
    {
        for(int i =0;i<pendingRequests.size();i++)
        {
            User x = pendingRequests.get(i);
            System.out.println((i + 1) + " " + x.getUserName() + " " + x.getEmail());
        }
        System.out.println("Choose the user you want to select");
        int x = Main.input.nextInt();
        int index = x-1;
        System.out.println("1 - Confirm request");
        System.out.println("2 - Decline request");
        int choice = Main.input.nextInt();
        if(choice == 1)
            return true;
        else
            return false;

    }

    public static void addfriend()
    {
        //        1. omar omarkhalid@hotmail.com
        //        2. omar omar@hotmail.com
        //
        //        addFriend Task
        //
        //
        //
        //        Steps :
        //
        //        user search for a username --> done
        //        fill array with usernames and emails assigned to them --> done
        //        print the array indexed to its size --> done
        //        let the user who searches choose the index he wants to add --> done
        //        when he chooses the index he wants to add his username should be passed to the array of pending requestes to the other user -- > done
        //        the other user sees the pending requests array indexed --> done
        //        the other uses chooses wether he wants to confirm the request or decline --> done
        //        if decline then delete the chosen index from the pending request array
        //        if he confirms then create a new object from the friend class
    }

}