package RealEstate.Exceptions;

public class UnAuthorizedUser extends RuntimeException{
    public UnAuthorizedUser()
    {
        super("This user is not authorized to perform such action!");
    }

}
