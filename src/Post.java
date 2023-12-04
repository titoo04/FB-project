import java.util.*;
public class Post {
    private static String postContent;

    private int ID;

    private ArrayList <Integer> taggedUsers = new ArrayList<>();

    private int privacyOptions;
    private ArrayList <Comment> comments= new ArrayList<>();
    private int reacts = 0;
    public Post(String content,int privacyOptions)//,tags)
    {
        this.postContent=content;
        this.ID=User.feed.size()+1;
        this.privacyOptions=privacyOptions;
        this.reacts=0;
    }

    public static void createPost()
    {
        System.out.println("What's on your mind");
        postContent = Main.input.next();

        makeTags();
        int privacyNum = privacy();

        Post post = new Post(postContent, privacyNum);//,tags );
    }

    private static void makeTags()
    {
        System.out.println("If you want to tag someone enter 1 else enter any character ");
        String tagsOptions=Main.input.next();
        if (tagsOptions.equals("1"))
        {
            System.out.println("enter the user");
            //search
            //choose
            //insert in taggedUsers
            System.out.println("1)Enter another friend?");
            System.out.println("Enter any character to exit");
            String addOption = Main.input.next();

            if(addOption.equals("1")) makeTags();
        }
    }
    private static int privacy()
    {
        System.out.println("Enter 1 for public ");
        System.out.println("Enter 2 for friends only ");
        System.out.println("Enter 3 for private ");
        String privacyOptions=Main.input.next();
        if (privacyOptions.equals("1"))
        {
            //public post
            return 1;
        }
        else if (privacyOptions.equals("2"))
        {
            //friends only
            return 2;
        }
        else if (privacyOptions.equals("3"))
        {
            //private
            return 3;
        }
        else
        {
            System.out.println("Invalid input, Choose 1,2,3");
            return privacy();
        }
    }
}