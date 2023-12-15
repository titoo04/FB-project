
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
        if(LogIn.loggedIn.postsCreated.size()==0)
            System.out.println("No posts to show (You haven' created a post before)");
        else
        {
            for(Post x: LogIn.loggedIn.postsCreated)
            {
                System.out.println(x.getPostContent());
            }
        }
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