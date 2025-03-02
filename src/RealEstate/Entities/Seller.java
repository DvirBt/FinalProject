package RealEstate.Entities;

/**
 * This class represents a Seller - which inherits from person.
 */
public class Seller extends Person{

    public Seller(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Seller);
    }
}
