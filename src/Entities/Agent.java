package Entities;

public class Agent implements Person{

    @Override
    public void allowedActions()
    {
        System.out.println("Hello Agent!\nWhat action would you like to perform?");
        System.out.println("");
    }

}
