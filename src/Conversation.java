import java.io.*;
import java.util.*;

public class Conversation
{
    public ArrayList<User>participants=new ArrayList<>();
    protected ArrayList<String>chat= new ArrayList<>();
    private static int id;
    public Conversation(User user)
    {

       // LoadConversationsFromFile(user);
        DisplayConvos(user);
        writeConversationsInFile(user);
    }
    protected void LoadConversationsFromFile(User user)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("conversations.text")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    protected void writeConversationsInFile(User user)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations.txt", true))) {
            for (Conversation conversation: user.convos)
            // for (int i=0;i<user.convos.length();i++)
            {
                writer.write("Participants: ");
                for (User participant:participants)
                {
                    writer.write(String.valueOf(participant.getUserName()+' '));
                }
                writer.newLine();
                for (String line: conversation.chat)
                {
                    writer.write(String.valueOf(line));
                    writer.newLine();
                }

            }
            System.out.println("Data has been written to file successfully.");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void DisplayConvos(User user )
    {
        for (Conversation conversation:user.convos)
        {
            System.out.println(conversation.chat);
            System.out.println("Press 1) to send a message");
            System.out.println("Press 2) to add participants");
            System.out.println("Press 3) to skip to next conversation");
            String choice=Main.input.next();
            switch (choice) 
            {
                case "1":
                {
                    sendMessage(conversation,user);
                }
                case "2":
                {
                    addParticipants(participants);
                }
                case "3":
                {
                    continue;
                }
            }
            
        }
    }
    public void sendMessage(Conversation conversation, User user)
    {
        String message=Main.input.next();
        conversation.chat.add(user.getUserName()+ ": "+ message);
    }
    public void addParticipants(ArrayList<User>participants)
    {
        boolean found = false;
        String name= Main.input.next();
        for (User participant:participants)
        {
            if(participant.getUserName().equals(name))
            {
                participants.add(participant);
                System.out.println("User added to conversation successfully");
                found =true;
                System.out.println("Do you want to add other participants?");
                break;
            }
        }
        if (!found)
        {
            System.out.println("There is no user that has that username");
        }
    }
}

