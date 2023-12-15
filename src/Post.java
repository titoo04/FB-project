import java.util.*;

public class Post {
    private int ID;

    private String postContent;
    private int userId;
    private int privacyOptions;
    private int reacts = 0;
    public  ArrayList <Comment> comments= new ArrayList<>();
    private ArrayList <Integer> taggedUsers = new ArrayList<>();

    public Post(String content,int privacyOptions)//,tags)
    {
        this.postContent=content;
        //this.ID=User.feed.size()+1;
        this.privacyOptions=privacyOptions;
        this.reacts=0;
    }

    public void createPost(Post p)
    {

        switch (p.getPrivacyOptions())
        {
            case 1:
                //add friends of user (all)
                for(Friend f:User.friends)
                {
                    f.feed.add(0, p);
                }
                LogIn.loggedIn.postsCreated.add(0, p);
                break;
            case 2:
                //add to friends of user (not restricted)
                for(Friend f:User.friends)
                {
                    if(!f.isRestricted()) f.feed.add(0, p);
                }
                LogIn.loggedIn.postsCreated.add(0, p);
                break;
            case 3:
                LogIn.loggedIn.postsCreated.add(0, p); // add post to profile

        }
    }

    private void makeTags()
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

    public static int privacy()
    {
        System.out.println("Enter 1 for public ");
        System.out.println("Enter 2 for restricted friends ");
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
            System.out.println("Invalid input, Choose 1, 2, or 3");
            return privacy();
        }
    }

    public static void viewPosts(ArrayList<Post> posts)
    {
        int idx = 0;
        for (Post p:posts)
        {
            System.out.println(p.getPostContent());
            System.out.println("Reacts " + p.getReacts());
            System.out.println("1)Like");
            System.out.println("2)View comments");
            System.out.println("3)Skip post");

            int ctr = 0;
            boolean validInput = false;
            do
            {
                if(ctr>0) System.out.println("1)Like 2)View comments 3)Skip post");
                String friendsPostOption = Main.input.next();
                String operationOption;
                switch (friendsPostOption)
                {
                    case "1":
                        p.reacts++;
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Next post");
                        //System.out.println("3)Return to profile");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<posts.size())validInput = true;
                            else
                            {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "2":
                        //openComments
                        Comment.viewComments(p);
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Exit");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<posts.size())validInput = true;
                            else
                            {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "3":
                        idx++;
                        if(idx<posts.size())validInput = true;
                        else
                        {
                            System.out.println("You have reached end of posts");
                            validInput = true;
                        }
                        break;
                    default:
                        System.out.println("Invalid input, please enter 1 or 2");
                }
            }
            while(!validInput);
        }
    }

    public static void viewProfilePosts(ArrayList<Post> posts)
    {
        int idx = 0;
        for (Post p:posts)
        {
            System.out.println(p.getPostContent());
            System.out.println("Reacts " + p.getReacts());
            System.out.println("1)Like");
            System.out.println("2)View comments");
            System.out.println("3)Edit post");
            System.out.println("4)Skip post");

            int ctr = 0;
            boolean validInput = false;
            do
            {
                if(ctr>0) System.out.println("1)Like 2)View comments 3)Edit post 4)Skip post");
                String friendsPostOption = Main.input.next();
                String operationOption;
                switch (friendsPostOption)
                {
                    case "1":
                        p.reacts++;
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Next post");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<posts.size())validInput = true;
                            else
                            {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "2":
                        Comment.viewComments(p);
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Exit");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<posts.size())validInput = true;
                            else
                            {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "3":
                        System.out.println("Enter edited post");
                        p.setPostContent(Main.input.next());
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Exit");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<posts.size())validInput = true;
                            else
                            {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "4":
                        idx++;
                        if(idx<posts.size())validInput = true;
                        else
                        {
                            System.out.println("You have reached end of posts");
                            validInput = true;
                        }
                        break;
                    default:
                        System.out.println("Invalid input, please enter 1 or 2");
                }
            }
            while(!validInput);
        }
    }

    public static void noFriendsPosts()
    {
        System.out.println("No posts to show");
        System.out.println("1)Add friends?");
        System.out.println("2)Go back");

        boolean validInput = false;
        while(!validInput)
        {
            String emptyFriendsPosts = Main.input.next();
            switch (emptyFriendsPosts) {
                case "1":
                    //addfriend()
                    validInput = true;
                    break;
                case "2":
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input, please enter 1 or 2");
            }
        }
    }

    public static void noProfilePosts()
    {
        System.out.println("No posts to show");
        System.out.println("1)Create a post");
        System.out.println("2)Go back");

        String operationOption;
        boolean validInput = false;
        while(!validInput)
        {
            String emptyFriendsPosts = Main.input.next();
            switch (emptyFriendsPosts) {
                case "1":
                    System.out.println("What's on your mind");
                    String postContent = Main.input.next();
                    //makeTags();
                    int privacyNum = Post.privacy();
                    Post post = new Post(postContent, privacyNum);
                    //,tags );
                    post.createPost(post);

                    System.out.println("1)Do another operation?");
                    System.out.println("2)Exit");

                    operationOption = Main.input.next();
                    if(operationOption.equals("2"))validInput = true;
                    break;
                case "2":
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input, please enter 1 or 2");
            }
        }
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostContent() {
        return postContent;
    }

    public int getPrivacyOptions() {
        return privacyOptions;
    }

    public void setPrivacyOptions(int privacyOptions) {
        this.privacyOptions = privacyOptions;
    }

    public int getReacts() {
        return reacts;
    }

    public void setReacts(int reacts) {
        this.reacts = reacts;
    }
}