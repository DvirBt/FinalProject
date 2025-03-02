package RealEstate.Entities;

import RealEstate.Asset;

import java.util.ArrayList;

/**
 * This class represents a Buyer - which inherits from person.
 */
public class Buyer extends Person{

    public Buyer(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Buyer);
    }
}
