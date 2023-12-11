public class Profile
{
    public Profile()
    {
    }
    public void viewProfile(User u)
    {
        System.out.println(u.getUserName());
        System.out.println(u.getBirthDate());
        System.out.println(u.getGender());
        System.out.println(u.getEmail());
    }
    public void viewPosts(User u)
    {
        for(Post x: LogIn.loggedIn.postsCreated)
        {
            System.out.println(x.getPostContent());
        }
    }
    public void viewFriendsList()
    {
    }
}