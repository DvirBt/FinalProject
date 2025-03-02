package RealEstate.Exceptions;

/**
 * This exception is thrown when not enough or more than needed data is entered by a user.
 */
public class InvalidData extends RuntimeException {
    public InvalidData()
    {
        super("Not enough or more than needed inputs entered!");
    }
}
