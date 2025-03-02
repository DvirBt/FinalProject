package RealEstate.Exceptions;

/**
 * This exception is thrown when an unregistered user tries to log into the system.
 */
public class UnRegisteredUser extends Exception {
    public UnRegisteredUser()
    {
        super("This user is not registered, try again!");
    }
}
