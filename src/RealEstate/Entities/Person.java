package RealEstate.Entities;

/**
 * This class represents a person in the system.
 */
public abstract class Person {

    protected String name;
    protected Role role;

    public Person(String name)
    {
        this.name = name;
        role = null;
    }

    public Person(String name, Role role)
    {
        this(name);
        this.role = role;
    }

    public Role getRole()
    {
        return role;
    }
    public String getName() {
        return name;
    }
}
