import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

public class Register
{
    public Register() {}

    public static void registerUser() {
        System.out.println("Enter username: ");
        String userName = Main.input.next();

        System.out.println("Enter email: ");
        String email = Main.input.next();

        System.out.println("Enter password: ");
        String passWord = Main.input.next();

        System.out.println("Enter gender: ");
        String gender = Main.input.next();

        System.out.println("Enter birth date: ");
        String birthDate = Main.input.next();

        RegisteredUser newUser = RegisteredUser.registerUser(userName, email, passWord, gender, birthDate);

        while(userExists(newUser.getEmail()) || !validEmail(newUser)){
            checkEmail(newUser);
        }

        while(!validGender(newUser)){
            System.out.println("Unknown gender");
            newUser.setGender(Main.input.next());
        }

        while(!validDate(newUser)){
            System.out.println("Invalid date, please enter date in this format d-m-yyyy");
            newUser.setBirthDate(Main.input.next());
        }

        while(!validPassword(newUser)){
            System.out.println("Please enter another password");
            newUser.setPassWord(Main.input.next());
        }

        addUser(newUser);
    }

    private static void addUser(RegisteredUser u){
        Main.users.add(u);
        System.out.println("User made successfully");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(String.valueOf(u.getID()) + ' ' +
                    u.getUserName() + ' ' +
                    u.getEmail() + ' ' +
                    u.getPassWord() + ' ' +
                    u.getGender() + ' ' +
                    u.getBirthDate());
            writer.newLine();

            System.out.println("Data has been written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkEmail(RegisteredUser u){
        if (userExists(u.getEmail())) {
            System.out.println("Email already exists");
            u.setEmail(Main.input.next());
        }

        if(!validEmail(u)){
            System.out.println("Invalid email");
            u.setEmail(Main.input.next());
        }
    }

    public static boolean userExists(String email) {
        for (User x : Main.users) {
            if (x.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validEmail(RegisteredUser u) {
        String regex = "^[A-Za-z0-9_.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$";//pattern

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(u.getEmail());
        return matcher.matches();
    }

    public static boolean validPassword(RegisteredUser u) {
        if (u.getPassWord().length() < 8 || u.getPassWord().length() > 16) {
            System.out.println("Password length must be between 8 and 16 characters.");
            return false;
        }

        if (!containsUppercase(u.getPassWord())) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        if (!containsLowercase(u.getPassWord())) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        if (!containsDigit(u.getPassWord())) {
            System.out.println("Password must contain at least one digit.");
            return false;
        }

        return true;
    }

    private static boolean containsUppercase(String pw) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private static boolean containsLowercase(String pw) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private static boolean containsDigit(String pw) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private static boolean validGender(RegisteredUser u) {
        if(u.getGender().toLowerCase().equals("m") ||
                u.getGender().toLowerCase().equals("f") ||
                u.getGender().toLowerCase().equals("male") ||
                u.getGender().toLowerCase().equals("female"))
        {
            return true;
        }
        return false;
    }

    private static boolean validDate(RegisteredUser u) {
        String date = u.getBirthDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");//date format
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(date);
            if (date.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }
}