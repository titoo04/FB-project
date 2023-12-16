import java.util.*;

public class RegisteredUser extends User
{
    public RegisteredUser(int ID, String userName, String email, String passWord, String gender, String birthDate)
    {
        super(ID, userName, email, passWord, gender, birthDate);
    }
    public RegisteredUser()
    {}

    public static RegisteredUser registerUser(String name, String email, String passWord, String gender, String birthDate)
    {
        RegisteredUser newUser = new RegisteredUser
        (
                Main.users.size()+1,
                name,
                email,
                passWord,
                gender,
                birthDate
        );

        return newUser;
    }
}
