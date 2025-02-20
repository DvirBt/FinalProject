package Entities;

/**
 * This class represents a person in the system.
 */
public class Person {

    // Properties

    private String name;
    private String email;

    public Person(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    // Getters

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }
}
