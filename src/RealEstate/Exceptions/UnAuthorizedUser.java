package RealEstate.Exceptions;

/**
 * This Exception is thrown when a user tries to perform an action he is not allowed to.
 */
public class UnAuthorizedUser extends Exception{
    public UnAuthorizedUser()
    {
        super("This user is not authorized to perform such action!");
    }

}
