package RealEstate.Exceptions;

public class InvalidAddress extends RuntimeException{
    public InvalidAddress()
    {
        super("An asset already exists at this address!");
    }
}
