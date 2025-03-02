package RealEstate.Exceptions;

/**
 * This exception is thrown when a user tried to enter a choice out of the given range.
 */
public class InvalidChoice extends RuntimeException{
    public InvalidChoice(String message)
    {
        super(message);
    }
}
