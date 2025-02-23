package RealEstate.Entities;

import java.util.ArrayList;

public class Agent extends Person{

    private ArrayList<String> notifications;

    public Agent(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Agent);
        notifications = new ArrayList<>();
    }

    public void update(String message)
    {
        notifications.add(message);
    }

}
