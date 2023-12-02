import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

public class Register extends User
        {
    private ArrayList<User>users= new ArrayList<>();
    private boolean exists = false;
    static Scanner input = new Scanner(System.in);



    public Register() {
        loadUsersFromFile();

        this.userName = input.next();
        this.email = input.next();
        this.passWord = input.next();
        this.gender = input.next();
        this.birthDate = input.next();

        User user = new User(userName, email, passWord, gender, birthDate);

        while(userExists(user) || !validEmail(user)){
            checkEmail(user);
        }

        while(!validGender(user)){
            System.out.println("Unknown gender");
            user.setGender(input.next());
        }

        while(!validDate(user)){
            System.out.println("Invalid date, please enter date in this format d-m-yyyy");
            user.setBirthDate(input.next());
        }

        while(!validPassword(user)){
            System.out.println("Please enter another password");
            user.setPassWord(input.next());
        }

        addUser(user);
    }

    private void addUser(User u){
        users.add(u);
        System.out.println("User made successfully");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(u.userName + ' ' + u.email + ' ' + u.passWord + ' ' + u.gender + ' ' + u.birthDate);
            writer.newLine(); // Add a newline for each entry

            System.out.println("Data has been written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkEmail(User u){
        if (userExists(u)) {
            while(userExists(u)){
                System.out.println("Already exists");
                u.email = input.next();
                exists = false;
            }
        }
        if(!validEmail(u)){
            while(!validEmail(u)){
                System.out.println("Invalid");
                u.setEmail(input.next());
            }
        }
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(" ");

                User user = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean userExists(User u) {
        for (User x : users) {
            if (x.getEmail().equals(u.getEmail())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean validEmail(User u) {
        String regex = "^[A-Za-z0-9_.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$";//pattern

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(u.getEmail());
        return matcher.matches();
    }

    public boolean validPassword(User u) {
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

    private boolean containsUppercase(String pw) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private boolean containsLowercase(String pw) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private boolean containsDigit(String pw) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(pw);
        return matcher.find();
    }

    private boolean validGender(User u) {
        if(u.getGender().toLowerCase().equals("m") ||
           u.getGender().toLowerCase().equals("f") ||
           u.getGender().toLowerCase().equals("male") ||
           u.getGender().toLowerCase().equals("female"))
        {
            return true;
        }
        return false;
    }

    private boolean validDate(User u) {
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
