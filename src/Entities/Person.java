package Entities;

/**
 * This class represents a person in the system.
 */
public abstract class Person {
    protected Role role;

    public Person(Role role)
    {
        this.role = role;
    }

    public abstract void allowedActions();
}
