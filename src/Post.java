import java.util.*;

public class Post {
    private int ID;
    //tags
    private int privacyOptions;//1->public   2->friends only
    private ArrayList <Comment> comments= new ArrayList<>();
    private int reacts = 0;
}