import  java.util.*;

public abstract class User
{
    private int ID;
    private String userName;
    private String email;
    private String passWord;
    private String gender;
    private String birthDate;
    public static ArrayList<Post> postsCreated = new ArrayList<>();
    public ArrayList<Post> feed = new ArrayList<>();
    public ArrayList<Post> friendsPosts = new ArrayList<>();
    public static ArrayList<Friend> friends = new ArrayList<>();

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
                Main.usersToshow.add(new Pair<>(u.getUserName(), u.getEmail()));
            }
            if (found)
            {
                for(Pair<String, String> x:Main.usersToshow)
                {
                    System.out.println(x.getKey()+ " " +x.getValue());
                }
            }
            else
                System.out.println("No friends found");
        }
    }

    public static void addfriend()
    {

    }

}