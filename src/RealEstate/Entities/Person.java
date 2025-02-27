package RealEstate.Entities;

import Notification.Observer;

import java.util.ArrayList;

/**
 * This class represents a person in the system.
 */
public abstract class Person implements Observer {

    protected String firstName;
    protected String lastName;
    protected Role role;
    protected ArrayList<String> notifications;

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        role = null;
        notifications = new ArrayList<>();
    }

    public Person(String firstName, String lastName, Role role)
    {
        this(firstName, lastName);
        this.role = role;
    }

    public Role getRole()
    {
        return role;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    /**
     * This function checks if a given first name and last name equals to this person.
     * @param firstName - the first name.
     * @param lastName - the last name.
     * @return true if this is the same person. Otherwise returns false.
     */
    public boolean samePerson(String firstName, String lastName)
    {
        if (this.firstName.equals(firstName) && this.lastName.equals(lastName))
            return true;

        return false;
    }
    public String toString()
    {
        return firstName + " "  +lastName;
    }

    @Override
    public void update(String message)
    {
        notifications.add(message);
        System.out.println(toString() + " received the message: " + message + " successfully");
    }
}
