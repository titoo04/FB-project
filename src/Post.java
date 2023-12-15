import java.util.*;

public class Post {
    private int ID;

    private static String postContent;

    private int privacyOptions;

    private int reacts = 0;
    public static ArrayList <Comment> comments= new ArrayList<>();
    private ArrayList <Integer> taggedUsers = new ArrayList<>();
    public Post(String content,int privacyOptions)//,tags)
    {
        this.postContent=content;
        //this.ID=User.feed.size()+1;
        this.privacyOptions=privacyOptions;
        this.reacts=0;
    }

    public static void createPost()
    {
        System.out.println("What's on your mind");
        postContent = Main.input.next();
        //makeTags();
        int privacyNum = privacy();
        Post post = new Post(postContent, privacyNum);
        //,tags );
        switch (privacyNum)
        {
            case 1:
               //add friends of user (all)
                for(User f:User.friends)
                {
                  f.feed.add(post);
              }
            case 2:
                //add to friends of user (not restricted)
//                for(User f:User.friends)
//              {
//                 if(!f.isRestricted()) f.feed.add(post);
//              }
            case 3:
                LogIn.loggedIn.postsCreated.add(post); // add post to profile

        }
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
            System.out.println("Invalid input, Choose 1, 2, or 3");
            return privacy();
        }
    }

    public static void viewPosts(ArrayList<Post> posts)
    {
        int idx = 1;
        for (Post p:posts)
        {
            System.out.println(p.getPostContent());
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
                switch (friendsPostOption) {
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
                            if(idx<=posts.size())validInput = true;
                            else {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "2":
                        //openComments()
                        System.out.println("1)Do another operation?");
                        System.out.println("2)Exit");

                        operationOption = Main.input.next();
                        if(operationOption.equals("1")) ctr++;
                        else if(operationOption.equals("2"))
                        {
                            idx++;
                            if(idx<=posts.size())validInput = true;
                            else {
                                System.out.println("You have reached end of posts");
                                validInput = true;
                            }
                        }
                        break;
                    case "3":
                        idx++;
                        if(idx<=posts.size())validInput = true;
                        else
                        {
                            System.out.println("You have reached end of posts");
                            validInput = true;
                        }
                        break;
                    default:
                        System.out.println("Invalid input, please enter 1 or 2");
                }
            }while(!validInput);
        }
    }

    public static void viewProfilePosts(ArrayList<Post> posts)
    {
        int idx = 1;
        for (Post p:posts)
        {
            System.out.println(p.getPostContent());
            System.out.println("1)Like");
            System.out.println("2)View comments");
            System.out.println("3)Edit post");
            System.out.println("4)Delete post");
            System.out.println("5)Skip post");

            int ctr = 0;
            boolean validInput = false;
            do
            {
                if(ctr>0) System.out.println("1)Like 2)View comments 3)Edit post 4)Delete post 5)Skip post");
                String friendsPostOption = Main.input.next();
                String operationOption;
                switch (friendsPostOption) {
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
                        //openComments()
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
                        // search for post in every user
                        // delete it their
                        posts.remove(p);
                        break;
                    case "5":
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
            }while(!validInput);
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
                    createPost();

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

    public static void setPostContent(String postContent) {
        Post.postContent = postContent;
    }
    public static String getPostContent() {
        return postContent;
    }

}