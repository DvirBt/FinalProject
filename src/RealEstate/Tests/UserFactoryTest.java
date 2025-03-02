package RealEstate.Tests;

import RealEstate.Entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    @DisplayName("Creates user based on a given role number")
    void createUserType()
    {
        UserFactory factory = new UserFactory();
        Person p = factory.createUserType("Dvir", "Bto", 1);
        boolean instance = false;
        if (p instanceof Agent)
            instance = true;
        assertTrue(instance);
        p = factory.createUserType("Dvir", "Bto", 2);
        instance = false;
        if (p instanceof Buyer)
            instance = true;
        assertTrue(instance);
        p = factory.createUserType("Dvir", "Bto", 3);
        instance = false;
        if (p instanceof Seller)
            instance = true;
        assertTrue(instance);
    }
}