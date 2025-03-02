package RealEstate.Entities;

import RealEstate.Notification.Observer;

import java.util.ArrayList;

/**
 * This class represents a person in the system which contains an id, full name, his meaning for the system and all the messages that was sent to him.
 */
public abstract class Person implements Observer {

    protected static int ID = 1; // each person will have unique id
    protected int id;
    protected String firstName;
    protected String lastName;
    protected Role role;
    protected ArrayList<String> notifications;

    public Person(String firstName, String lastName)
    {
        id = ID++;
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
    public int getId()
    {
        return id;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
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

    /**
     * This function gets a message and delivers it to this person
     * @param message the message that was sent by a owner of an asset.
     */
    @Override
    public void update(String message)
    {
        notifications.add(message);
        System.out.println(toString() + " received the message: " + message + " successfully");
    }
}
