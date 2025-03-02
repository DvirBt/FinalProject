package RealEstate.Tests;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Agent agent1 = new Agent("Dvir", "Bto");
    Agent agent2 = new Agent("Dvir", "Bt");
    @Test
    @DisplayName("Checks if two persons are the same one")
    void samePerson()
    {
        assertFalse(agent1.samePerson(agent2.getFirstName(), agent2.getLastName()));
        Agent agent3 = new Agent("Dvir", "Bto");
        assertTrue(agent1.samePerson(agent1.getFirstName(), agent3.getLastName()));
    }

    @Test
    @DisplayName("Return the details of the person")
    void testToString()
    {
        String expected = "Dvir Bto";
        String result = agent1.toString();
        assertEquals(expected, result);
        assertNotEquals(expected, agent2.toString());
    }
}