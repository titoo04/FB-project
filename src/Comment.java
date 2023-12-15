import java.util.*;

public class Comment {
    private int UserID;
    private String content;
    private  ArrayList<Pair<String, Integer>> replies= new ArrayList<>();
    private int reacts = 0;

    public Comment(int userId, String content)
    {
        this.UserID = userId;
        this.content=content;
    }

    public static void viewComments(Post p)
    {
        if (p.comments.isEmpty())
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
                        Main.input.nextLine();
                        String writeComment=Main.input.nextLine();
                        Comment comment=new Comment(LogIn.loggedIn.getID(), writeComment);
                        p.comments.add(0, comment);
                        validInput = true;
                        viewComments(p);
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
            for (Comment comment : p.comments)
            {
                if(backToPost) break;
                System.out.println(comment.getContent());
                System.out.println("Reacts " + comment.getReacts());
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
                            else if(operationOption1.equals("2"))
                            {
                                validInput = true;
                                if(++idx == p.comments.size())
                                {
                                    System.out.println("You have reached the last comment");
                                }
                            }
                            break;
                        case "2": //Reply
                            System.out.println("Write a reply...");
                            Main.input.nextLine();
                            String writeReply = Main.input.nextLine();
                            Pair<String,Integer> reply = new Pair<>(writeReply,0);
                            comment.replies.add(reply);
                            viewReplies(comment); //view Replies

                            System.out.println(comment.getContent());
                            System.out.println("Reacts " + comment.getReacts());
                            System.out.println("1)Do another operation?");
                            System.out.println("2)Next comment ");
                            operationOption1 = Main.input.next();
                            if(operationOption1.equals("1")) cnt++;
                            else if(operationOption1.equals("2"))
                            {
                                validInput = true;
                                if(++idx == p.comments.size())
                                {
                                    System.out.println("You have reached the last comment");
                                }
                            }
                            break;
                        case "3": //Skip Comment
                            validInput = true;
                            if(++idx == p.comments.size())
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

    public static void viewReplies(Comment c)
    {
        if (c.replies.isEmpty())
        {
            System.out.println("No Replies ");
            boolean validInput = false;
            System.out.println("Write a reply? (y/n) ");
            do
            {
                String replyChoice = Main.input.next();
                switch(replyChoice.toLowerCase())
                {
                    case "y":
                        Main.input.nextLine();
                        String writeReply=Main.input.nextLine();
                        Pair<String,Integer> rep=new Pair<>(writeReply,0);
                        c.replies.add(0, rep);
                        validInput = true;
                        viewReplies(c);
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
            boolean backToComment = false;
            for (Pair<String,Integer> r : c.replies)
            {
                if(backToComment) break;
                System.out.println(r.getKey());
                System.out.println("Reacts "+r.getValue());
                System.out.println("1)React ");
                System.out.println("2)Skip reply ");
                System.out.println("3)Return to comment ");

                int cnt = 0;
                boolean validInput = false;
                do
                {
                    if(cnt>0) System.out.println("1)React 2)Skip reply 3)Return to comment ");
                    String friendsReplyOption = Main.input.next();
                    String operationOption1;
                    switch (friendsReplyOption)
                    {
                        case "1": //Reacts
                            r.setValue(r.getValue() + 1);
                            System.out.println("1)Do another operation?");
                            System.out.println("2)Next reply ");
                            operationOption1 = Main.input.next();
                            if(operationOption1.equals("1")) cnt++;
                            else if(operationOption1.equals("2"))
                            {
                                validInput = true;
                                if(++idx > c.replies.size())
                                {
                                    System.out.println("You have reached the last reply ");
                                }
                            }
                            break;
                        case "2": //Skip Comment
                            validInput = true;
                            if(++idx == c.replies.size())
                            {
                                System.out.println("You have reached the last reply ");
                            }
                            continue;
                        case "3": //Return
                            validInput= true;
                            backToComment = true;
                    }
                }
                while (!validInput);
            }
        }
    }

    public String getContent()
    {
        return content;
    }
    public void setContent(String commentContent)
    {
        content = commentContent;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getReacts() {
        return reacts;
    }

    public void setReacts(int reacts) {
        this.reacts = reacts;
    }
}