
public class Profile
{
    public Profile()
    {
    }
    public void viewProfile(User u)
    {
        System.out.println(u.getName());
        System.out.println(u.getBirthDate());
        System.out.println(u.getGender());
        System.out.println(u.getEmail());
    }
    public void viewPosts(User u)
    {
        if(LogIn.loggedIn.getPostsCreated().size()==0)
            System.out.println("No posts to show (You haven' created a post before)");
        else
        {
            for(Post x: LogIn.loggedIn.getPostsCreated())
            {
                System.out.println(x.getPostContent());
            }
        }
    }
    public void viewFriendsList(User u)
    {
        System.out.println(u.getFriends().size());
        System.out.println("Your friends :");
        for(int i =0;i<LogIn.loggedIn.getFriends().size();i++)
        {
            User friend = LogIn.loggedIn.getFriends().get(i);
           System.out.println(friend.getName());
        }

    }
}