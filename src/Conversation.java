import java.io.*;
import java.util.*;

public class Conversation
{
    public ArrayList<User>participants=new ArrayList<>();
    protected ArrayList<String>chat= new ArrayList<>();
    private int id;
    private static int conversationsCounter=0;
    public Conversation()
    {
        this.id=++conversationsCounter;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected void DisplayConvos(User user )
    {
        if(!user.convos.isEmpty())
        {
            for (Conversation conversation : user.convos)
            {
                System.out.println("Conversation Id:"+conversation.id);
                System.out.print("Participants: ");
                for (User participant:conversation.participants)
                    System.out.print(participant.getName()+",");
                System.out.println();
                for (String msg:conversation.chat)
                {
                    System.out.println(msg);
                }
                System.out.println("Press 1) to send a message");
                System.out.println("Press 2) to add participants");
                System.out.println("Press 3) to skip to next conversation");
                System.out.println("Press any other key to return to main menu");
                String choice = Main.input.next();
                switch (choice)
                {
                    case "1":
                    {
                        sendMessage(conversation, user);
                        DisplayConvos(user);
                        break;
                    }
                    case "2":
                    {
                        addParticipants(user, conversation);
                        break;
                    }
                    case "3":
                    {

                        break;
                    }
                }
            }
        }
        else
        {
            System.out.println("there are no conversations press 1 to add one");
            String choice = Main.input.next();
            if (choice.equals("1"))
            {
                Conversation conversation = new Conversation();
                conversation.chat.add("Chat is empty");
                conversation.participants.add(user);
                user.convos.add(conversation);
                DisplayConvos(user);
            }
        }
    }

    public void sendMessage(Conversation conversation, User user)
    {
        if (conversation.participants.size()!=1)
        {
            Main.input.nextLine();
            String message = Main.input.nextLine();
            if (conversation.chat.get(0).equals("Chat is empty"))
            {
                conversation.chat.clear();
            }
            conversation.chat.add(user.getName() + ": " + message);
        }
        else
        {
            System.out.println("Add participants First");
        }
    }
    public void addParticipants(User user, Conversation conversation)
    {
        boolean found=false;
        String name= Main.input.next();
        for (User participant:Main.users)
        {
            if (participant.getName().equals(name))
            {
                found =true;
                conversation.participants.add(participant);
                participant.convos.add(conversation);
                System.out.println("User added to conversation successfully");
                System.out.println("Do you want to add other participants?");
                String Choice = Main.input.next();
                if (Choice.equals("y"))
                    addParticipants(user,conversation);
                else if (Choice.equals("n"))
                    DisplayConvos(user);
            }
        }
        if (!found)
        {
            System.out.println("There is no user that has that username");
            System.out.println("Do you want to try again?");
            String Choice=Main.input.next();
            if(Choice.equals("y"))
                addParticipants(user,conversation);
            else if (Choice.equals("n"))
            {
                DisplayConvos(user);
            }
        }
    }
}