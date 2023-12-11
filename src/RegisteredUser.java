import java.util.*;

public class RegisteredUser extends User
{
    Scanner input = new Scanner(System.in);
    public RegisteredUser(int ID, String userName, String email, String passWord, String gender, String birthDate)
    {
        super(ID, userName, email, passWord, gender, birthDate);
    }

    public static RegisteredUser registerUser(String userName, String email, String passWord, String gender, String birthDate)
    {
        RegisteredUser newUser = new RegisteredUser
        (
                Main.users.size()+1,
                userName,
                email,
                passWord,
                gender,
                birthDate
        );

        return newUser;
    }
}
