public class HandlingExceptions extends Exception
{
    public HandlingExceptions(String operation, String fileName, Throwable cause)
    {
        super("Error during " + operation + " operation on file '" + fileName + "'.", cause);
    }

    public HandlingExceptions(String fileName, boolean isEmptyFile) {
        super("Error: The file '" + fileName + "' is empty.");
    }
}