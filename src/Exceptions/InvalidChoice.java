package Exceptions;

public class InvalidChoice extends RuntimeException{
    public InvalidChoice()
    {
        super("The given input is invalid!\nPlease enter a number between 1-4");
    }
}
