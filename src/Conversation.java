import java.io.*;
import java.util.*;

public class Conversation
{
    public ArrayList<User>participants=new ArrayList<>();
    private int chatSize,size;
    protected ArrayList<String>chat= new ArrayList<>();
    private static int id;
    public Conversation(User user)
    {}
    protected void LoadConversationsFromFile(ArrayList<User> user)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("conversations.txt")))
        {
            for (User uSer:Main.users)
            {
                String line;

                while ((line = reader.readLine()) != null)
                {
                    line= reader.readLine();
                    try
                    {
                         size = Integer.parseInt(line);
                        for (int j = 0; j < size ; j++)
                        {
                           line= reader.readLine();
                         try
                            {
                                chatSize= Integer.parseInt(line);
                                for (int k = 0; k < chatSize ; k++)
                                {
                                    chat.add(reader.readLine());
                                }
                            }
                         catch (NumberFormatException ex)
                         {
                             ex.printStackTrace();
                         }
                        }
                    }
                    catch (NumberFormatException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }
             catch(IOException e)
                {
                    e.printStackTrace();
                }
    }


    protected void writeConversationsInFile(ArrayList<User> users)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations.txt", true))) {
            for (User user:users)
            {
                writer.write(String.valueOf(user.convos.size()));
                writer.newLine();
                for (Conversation conversation: user.convos)
                {
//                writer.write("Participants: ");
//                for (User participant:participants)
//                {
//                    writer.write(participant.getUserName()+' ');
//                }
                    writer.write(String.valueOf(conversation.chat.size()));
                    writer.newLine();
                    for (String line: conversation.chat)
                    {
                        writer.write(line);
                        writer.newLine();
                    }

                }
                //System.out.println(chat.size());
                System.out.println("Data has been written to file successfully.");
            }
        } catch (IOException e)
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
                for (String msg:conversation.chat)
                {
                    System.out.println(msg);
                }
                System.out.println("Press 1) to send a message");
                System.out.println("Press 2) to add participants");
                System.out.println("Press 3) to skip to next conversation");
                String choice = Main.input.next();
                switch (choice)
                {
                    case "1":
                    {
                        sendMessage(conversation, user);
                        break;
                    }
                    case "2":
                    {
                        addParticipants(participants);
                        break;
                    }
                    case "3":
                    {
                        continue;
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
                    Conversation conversation = new Conversation(user);
                    conversation.chat.add("Chat is empty");
                    conversation.participants.add(user);
                    user.convos.add(conversation);
                    System.out.println(user.convos.size());
                    DisplayConvos(user);
                }
        }
    }
    public void sendMessage(Conversation conversation, User user)
    {
        String message=Main.input.next();
        //++chatSize;
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

            }
            if (found)
            {
                System.out.println("Do you want to add other participants?");
                addParticipants(participants);
                break;
            }
        }
        if (!found)
        {
            System.out.println("There is no user that has that username");
        }
    }
}

