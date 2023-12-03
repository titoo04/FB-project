import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;

public class Conversation
{
    public ArrayList<User>participants=new ArrayList<>();
    protected ArrayList<String>chat= new ArrayList<>();
    private static int id;
    public Conversation(User user)
    {

        LoadConversationsFromFile(user);
        DisplayConvos(user);
        writeConversationsInFile();
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
    protected void writeConversationsInFile()
    {

    }
    protected void DisplayConvos(User user )
    {
        for (Conversation conversation:user.convos)
        {
            System.out.println(conversation.chat);
            System.out.println("Press 1) to send a message");
            System.out.println("Press 2) to add participants");
            System.out.println("Press 3) to skip to next conversation");
            String choice=Register.input.next();
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
        String message=Register.input.next();
        conversation.chat.add(user.userName + ": "+ message);
    }
    public void addParticipants(ArrayList<User>participants)
    {
        boolean found = false;
        String name= Register.input.next();
        for (User participant:participants)
        {
            if(participant.userName.equals(name))
            {
                participants.add(participant);
                System.out.println("User added to conversation successfully");
                found =true;
                break;
            }
        }
        if (!found)
        {
            System.out.println("There is no user that has that username");
        }
    }
}

