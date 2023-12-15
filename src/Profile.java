public class Profile
{
    public Profile()
    {}
    public void viewProfile(User u)
    {
        System.out.println("Username: " +u.getUserName());
        System.out.println("Birthdate: " +u .getBirthDate());
        System.out.println("Gender: " +u.getGender());
        System.out.println("Email: " +u.getEmail());
    }
    public void viewFriendsList(User u)
    {
        System.out.println(u.friends.size());
        System.out.println("Your friends :");
        for(int i =0;i<LogIn.loggedIn.friends.size();i++)
        {
            User friend = LogIn.loggedIn.friends.get(i);
            System.out.println(friend.getUserName());
        }
}
}
