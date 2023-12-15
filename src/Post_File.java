//import java.io.*;
//import java.util.ArrayList;
//
//public class Post_File
//{
//    public void writePostToFile()
//    {
//        try
//        {
//            FileWriter fileWriter = new FileWriter("Post.txt");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//            for (Comment comment : Post.comments)
//            {
//                bufferedWriter.write("Comment: " + comment.getContent() + "\n");
//                bufferedWriter.write("Reacts: " + comment.reacts + "\n");
//
//                for (Pair<String, Integer> reply : comment.replies)
//                {
//                    bufferedWriter.write("Reply: " + reply.getKey() + "\n");
//                    bufferedWriter.write("Reply Reacts: " + reply.getValue() + "\n");
//                }
//                bufferedWriter.write("\n");
//            }
//
//            bufferedWriter.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void readPostFromFile()
//    {
//        try
//        {
//            FileReader fileReader = new FileReader("Post.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//            while ((line = bufferedReader.readLine()) != null)
//            {
//                System.out.println(line);
//            }
//
//            bufferedReader.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//}
