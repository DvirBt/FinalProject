package RealEstate.Exceptions;

public class UnauthorizedUser extends RuntimeException{
    public UnauthorizedUser()
    {
        super("This user is not authorized to do such action!");
    }

}
