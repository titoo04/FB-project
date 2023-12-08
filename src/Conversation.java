import java.io.*;
import java.util.*;

public class Conversation
{
    public ArrayList<User>participants=new ArrayList<>();
    protected ArrayList<String>chat= new ArrayList<>();
    private static int id;
    public Conversation()
    {}
    protected void LoadConversationsFromFile(ArrayList<User> users)
    {
        int indx=0;
        try (BufferedReader reader = new BufferedReader(new FileReader("conversations.txt")))
        {
            String line;
            while ((line= reader.readLine())!=null)
           {
               int size = Integer.parseInt(line);
               for (int j = 0; j < size; j++)
               {
                   line= reader.readLine();
                   if (line.startsWith("Participants:"))
                   {
                       // Extract names dynamically using a list
                       Conversation conversation= new Conversation();
                       users.get(indx).convos.add(conversation);

                       // Split line after "Participants:" and trim whitespace
                       String participantsList = line.substring(line.indexOf(":") + 2).trim();
                       System.out.println(participantsList);
                       // Loop until no more names are found
                       boolean hasNextName = true;
                       while (hasNextName) {
                           // Find the next comma or end of string
                           int commaIndex = participantsList.indexOf(",");
                           int endIndex = commaIndex == -1 ? participantsList.length() : commaIndex;

                           // Extract the current name and trim whitespace
                           String userName = participantsList.substring(0,endIndex).trim();
                           System.out.println(userName);
                           for (User participant:participants) {
                               if (participant.getUserName().equals(userName))
                               {
                                   System.out.println("in");
                                   users.get(indx).convos.get(j).participants.add(participant);
                               }
                           }

                           // Update participantsList for next iteration
                           if (commaIndex != -1) {
                               participantsList = participantsList.substring(endIndex + 1).trim();
                           } else {
                               hasNextName = false;
                           }
                       }


                       // Stop searching after finding participants list
                   }

                   int chatSize = Integer.parseInt(reader.readLine());
                   for (int k = 0; k < chatSize; k++)
                   {
                       users.get(indx).convos.get(j).chat.add(reader.readLine());
                   }

               }
               indx++;
           }


        }
             catch(IOException e)
                {
                    e.printStackTrace();
                }
//
    }


    protected void writeConversationsInFile(ArrayList<User> users)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations.txt", false)))
        {
            for (User user:users)
            {
                writer.write(String.valueOf(user.convos.size()));
                writer.newLine();
                for (Conversation conversation: user.convos)
                {
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
            System.out.println("Data has been written to file successfully.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void DisplayConvos(User user )
    {
        boolean found;
        if(!user.convos.isEmpty())
        {
            for (Conversation conversation : user.convos)
            {
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
                System.out.println("Press 4) to add conversation");
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
                        found=addParticipants(user,conversation);
                        if (found)
                        {
                            System.out.println("Do you want to add other participants?");
                            String Choice = Main.input.next();
                            if (Choice.equals("y"))
                                addParticipants(user,conversation);
                            else if (Choice.equals("n"))
                                DisplayConvos(user);
                            break;
                        }

                    if (!found)
                    {
                        System.out.println("There is no user that has that username");
                        String Choice=Main.input.next();
                        if(Choice.equals("y"))
                            addParticipants(user,conversation);
                        else if (Choice.equals("n"))
                            DisplayConvos(user);
                    }
                        break;
                    }
                    case "3":
                    {
                        continue;
                    }
                    case  "4":
                    {
                        AddConversation(user);
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
        String message=Main.input.next();
        if (conversation.chat.get(0).equals("Chat is empty"))
        {
            conversation.chat.clear();
            conversation.chat.add(user.getUserName()+ ": "+ message);
        }
        else
        {
            conversation.chat.add(user.getUserName() + ": " + message);
        }
    }
    public Boolean addParticipants(User user,Conversation conversation)
    {
        String name= Main.input.next();
        for (User participant:Main.users)
        {
            if (participant.getUserName().equals(name))
            {
                conversation.participants.add(participant);
                participant.convos.add(conversation);
                System.out.println("User added to conversation successfully");
                return true;
            }
        }
        return false;
    }
    public void AddConversation(User user)
    {
        Conversation newConversation= new Conversation();
        newConversation.chat.add("Chat is empty");
        newConversation.participants.add(user);
        System.out.println("Add participant: ");
        addParticipants(user,newConversation);
        user.convos.add(newConversation);
    }
}




