
public class Profile extends User{
    User u=new User();

    public Profile(User u) {
        this.u = u;
    }
    public void DisplayInfo() {
        System.out.println(u.getUserName());
        System.out.println(u.getBirthDate());
        System.out.println(u.getGender());
    }

}
