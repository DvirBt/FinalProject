package RealEstate.Tests;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Seller;
import RealEstate.Notification.Notification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    Notification notification = new Notification();
    Agent agent = new Agent("Dvir", "Bto");
    Seller seller = new Seller("Lotem", "Bto");
    Buyer buyer = new Buyer("Ayelet", "Bto");
    @Test
    @DisplayName("This function adds a new observer to the list")
    void register()
    {
        // no observers
        boolean result = notification.getObservers().isEmpty();
        assertTrue(result);
        notification.register(agent); // add observer 1
        result = notification.getObservers().contains(agent);
        assertTrue(result);
        notification.register(buyer); // add observer 2
        result = notification.getObservers().contains(buyer);
        assertTrue(result);
        notification.register(seller); // add observer 3
        result = notification.getObservers().contains(seller);
        assertTrue(result);
        assertEquals(3, notification.getObservers().size()); // make sure all 3 agent, seller, buyer exist in the list
    }

    @Test
    @DisplayName("This function sends a message to all the registered observers")
    void notifyAllObservers()
    {
        notification.register(agent);
        notification.register(buyer);
        boolean result = agent.getNotifications().isEmpty(); // no notifications -> true
        assertTrue(result);
        result = buyer.getNotifications().isEmpty(); // no notifications -> true
        assertTrue(result);
        notification.notifyAllObservers("Hi");
        result = agent.getNotifications().isEmpty(); // got 1 notification -> false
        assertFalse(result);
        result = buyer.getNotifications().isEmpty(); // got 1 notification -> false
        assertFalse(result);
        result = agent.getNotifications().contains("Hi"); // this is the message that was sent to the observer -> true
        assertTrue(result);
        result = buyer.getNotifications().contains("Hi"); // this is the message that was sent to the observer -> true
        assertTrue(result);

        // make sure that seller did not get the message because he is not registered
        result = seller.getNotifications().isEmpty(); // no notifications -> true
        assertTrue(result);
    }

    @Test
    @DisplayName("This function sends a message to a given observer")
    void notifyAgent()
    {
        notification.register(agent);
        boolean result = agent.getNotifications().isEmpty(); // no notifications -> true
        assertTrue(result);
        notification.notifyAgent(agent, "Hello");
        result = agent.getNotifications().isEmpty(); // got 1 notification -> false
        assertFalse(result);
        result = agent.getNotifications().contains("Hello"); // this is the message that was sent to him -> true
        assertTrue(result);
    }
}