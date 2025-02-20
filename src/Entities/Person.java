package Entities;

/**
 * This class represents a person in the system.
 */
public abstract class Person {
    protected Role role;

    public Person(int i)
    {
        role = Role.SelectRoleByInteger(i);
    }

    public Role getRole() {
        return role;
    }

    public abstract void allowedActions();

}
