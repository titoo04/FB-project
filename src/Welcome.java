import java.util.*;
public class Welcome
{
    public Welcome(User x)
    {
        displayMenu(x);
    }
    public void displayMenu(User x)
    {
        System.out.println("Welcome Back"+ x.userName);
        System.out.println("Press 1)Show feed");
        System.out.println("Press 2)View your profile");
        System.out.println("Press 3)Search for something");
        System.out.println("Press 4) to open you conversations");
        String choice= Register.input.next();
        switch (choice)
        {
            case "1":
            {
                viewFeed(x);
                break;
            }
            case "2":
            {
                viewProfile(x);
                break;
            }
            case "3":
            {
                searchBar(x);
                break;
            }
            default:
            {
                invalidSelection(x);
                break;
            }

        }
    }
    public void viewFeed(User x)
    {
        System.out.println('1');
        //Feed f= new Feed(x);
    }
    public void viewProfile(User x)
    {
        System.out.println('2');
        //Profile pf= new Profile(x);
    }
    public void searchBar(User x)
    {
        System.out.println('3');
        //        Search sch= new Search(x);
    }
    public void invalidSelection(User x)
    {
        System.out.println("Invalid Selection Please check your selection");
        displayMenu(x);
    }
}
