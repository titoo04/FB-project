import java.util.Scanner;

public class Register extends User
{
    public Register()
    {
        Scanner input = new Scanner(System.in);
        this.userName =input.next() ;
        this.email =input.next() ;
        this.passWord =input.next() ;
        this.gender  =input.next() ;
        this.birthDate =input.next() ;

        User user = new User(userName, email, passWord, gender, birthDate);
    }
}

