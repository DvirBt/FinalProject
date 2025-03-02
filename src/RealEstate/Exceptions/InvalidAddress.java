package RealEstate.Exceptions;

/**
 * This exception is thrown when a new asset is trying to be added on top of an existing asset.
 * Also thrown if a private asset is trying to be updated to public asset and the opposite.
 */
public class InvalidAddress extends RuntimeException{
    public InvalidAddress(String message)
    {
        super(message);
    }
}
