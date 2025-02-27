package Notification;

import RealEstate.Entities.Agent;

import java.util.ArrayList;

public class Notification {

    private ArrayList<Observer> observers = new ArrayList<>();

    public void register(Observer observer)
    {
        observers.add(observer);
    }

    public void notifyAllObservers(String message)
    {
        for (Observer observer : observers)
            observer.update(message);
    }

    public void notifyAgent(Agent agent, String message)
    {
        for (int i = 0; i < observers.size(); i++)
        {
            if (observers.get(i).equals(agent))
            {
                observers.get(i).update(message);
                break;
            }
        }
    }
}
