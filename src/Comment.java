import java.util.*;

public class Comment {
    private int ID;
    private String content;
    private ArrayList<Pair<String, Integer>> replies= new ArrayList<>();
    private int reacts = 0;
    public String getContent()
    {
        return content;
    }
    public void setContent(String commentContent)
    {
        content = commentContent;
    }
    public Comment(String content)
    {
      this.content=content;
    }
    public void viewComments()
    {
        if (Post.comments.isEmpty())
        {
            System.out.println("No comments ");
            boolean validInput = false;
            System.out.println("Write a comment? (y/n) ");
            do
            {
                String commentChoice = Main.input.next();
                switch(commentChoice.toLowerCase())
                {
                    case "y":
                        String writeComment=Main.input.next();
                        Comment comment=new Comment(writeComment);
                        Post.comments.add(comment);
                        validInput = true;
                        viewComments();
                        break;
                    case "n":
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid input, please enter (y/n)");
                }
            }
            while (!validInput);
        }
        else
        {
            int idx = 0;
            boolean backToPost = false;
            for (Comment comment : Post.comments)
            {
                if(backToPost) break;
                System.out.println(comment.getContent());
                System.out.println("1)React ");
                System.out.println("2)Reply ");
                System.out.println("3)Skip comment ");
                System.out.println("4)Return to post");

                int cnt = 0;
                boolean validInput = false;
                do
                {
                    if(cnt>0) System.out.println("1)React 2)Reply 3)Skip comment 4)Return to post");
                    String friendsCommentOption = Main.input.next();
                    String operationOption1;
                    switch (friendsCommentOption)
                    {
                        case "1": //Reacts
                            comment.reacts++;
                            System.out.println("1)Do another operation?");
                            System.out.println("2)Next comment ");
                            operationOption1 = Main.input.next();
                            if(operationOption1.equals("1")) cnt++;
                            else if(operationOption1.equals("2")){
                                validInput = true;
                                if(++idx == Post.comments.size())
                                {
                                    System.out.println("You have reached the last comment");
                                }
                            }
                            break;
                        case "2": //Reply
                            System.out.println("Write a reply...");
                            String writeReply=Main.input.next();
                            Pair<String,Integer> reply = new Pair<>(writeReply,0);
                            replies.add(reply);
                            System.out.println("1)Do another operation?");
                            System.out.println("2)Next comment ");
                            operationOption1 = Main.input.next();
                            if(operationOption1.equals("1")) cnt++;
                            else if(operationOption1.equals("2")){
                                validInput = true;
                                if(++idx == Post.comments.size())
                                {
                                    System.out.println("You have reached the last comment");
                                }
                            }
                            break;
                        case "3": //Skip Comment
                            if(++idx == Post.comments.size())
                            {
                                System.out.println("You have reached the last comment");
                            }
                            continue;
                        case "4": //Return
                            validInput= true;
                            backToPost = true;
                    }
                }
                while (!validInput);
            }

        }
    }
}