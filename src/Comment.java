import java.util.*;

public class Comment {
    private int ID;
    private ArrayList<Reply<String, Integer>> replies= new ArrayList<>();
    private int reacts = 0;
}