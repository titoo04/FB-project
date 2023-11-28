import  java.util.*;
public class User
{
    public String userName;
    public String email;
    protected String passWord;
    public String gender;
    public String birthDate;

    public User() {}

    public User(String userName, String email, String passWord, String gender, String birthDate)
    {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
