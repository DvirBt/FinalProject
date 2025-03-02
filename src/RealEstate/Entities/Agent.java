package RealEstate.Entities;

/**
 * This class represents an Agent - which inherits from person.
 */
public class Agent extends Person{

    public Agent(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Agent);
    }
}
