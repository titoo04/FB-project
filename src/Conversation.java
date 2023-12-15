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
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    protected void writeConversationsInFile(ArrayList<User> users)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations.txt", false)))
        {
            ArrayList<Integer>isAlreadyWritten=new ArrayList<>();
            for (User user:users)
            {
                for (Conversation conversation: user.convos)
                {
                    if (!isAlreadyWritten.contains(conversation.id))
                    {
                        isAlreadyWritten.add(conversation.id);
                        writer.write("Conversation Id: " + conversation.id);
                        writer.newLine();
                        writer.write("Participants: ");
                        for (User participant:conversation.participants)
                        {
                            writer.write(participant.getUserName()+',');
                        }
                        writer.newLine();
                        writer.write(String.valueOf(conversation.chat.size()));
                        writer.newLine();
                        for (String line: conversation.chat)
                        {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                }
            }
            System.out.println("Data has been written to file successfully.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void DisplayConvos(User user )
    {
        if(!user.convos.isEmpty())
        {
            for (Conversation conversation : user.convos)
            {
                System.out.println(user.getUserName());
                System.out.println("Conversation Id:"+conversation.id);
                System.out.print("Participants: ");
                for (User participant:conversation.participants)
                    System.out.print(participant.getUserName()+",");
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

                        continue;
                    }
                    default:
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
            conversation.chat.add(user.getUserName() + ": " + message);
        }
        else
        {
            System.out.println("Add participants First");
        }
    }
    public void addParticipants(User user,Conversation conversation)
    {
        boolean found=false;
        String name= Main.input.next();
        for (User participant:Main.users)
        {
            if (participant.getUserName().equals(name))
            {
                if (!conversation.participants.contains(participant))
                {
                    found = true;
                    conversation.participants.add(participant);
                    participant.convos.add(conversation);
                    System.out.println("User added to conversation successfully");
                }
                else
                {
                    System.out.println("User is already in this conversation");
                }
                System.out.println("Do you want to add other participants?");
                String Choice = Main.input.next();
                if (Choice.equals("y"))
                {
                    addParticipants(user, conversation);
                }
                else if (Choice.equals("n"))
                {
                    DisplayConvos(user);
                }
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
//    public Conversation addConversation(User user)
//    {
//        Conversation newConversation= new Conversation();
//        newConversation.chat.add("Chat is empty");
//        newConversation.participants.add(user);
//        System.out.println("Add participant: ");
//        addParticipants(user,newConversation);
//        return newConversation;
//    }
}