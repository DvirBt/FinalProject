package RealEstate.Entities;

import java.util.ArrayList;

public class Agent extends Person{

    private ArrayList<String> notifications;

    public Agent(String name)
    {
        super(name, Role.Agent);
        notifications = new ArrayList<>();
    }

    public void update(String message)
    {
        notifications.add(message);
    }

}
