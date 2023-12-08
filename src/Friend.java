public class Friend extends User
{
    public Friend()
    {}

    private boolean restricted;

    public boolean isRestricted()
    {
        return restricted;
    }

    public void setRestricted(boolean restricted)
    {
        this.restricted = restricted;
    }
}
