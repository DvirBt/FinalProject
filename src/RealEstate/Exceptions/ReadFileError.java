package RealEstate.Exceptions;

public class ReadFileError extends RuntimeException{
    public ReadFileError()
    {
        super("Read file failed!");
    }

}
