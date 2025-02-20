
/**
 * This class represents an address of an asset as a point in the grid.
 */
public class Address {

    // Properties
    private int street;
    private int boulevard;

    // Constructors
    public Address()
    {
        street = 0;
        boulevard = 0;
    }

    public Address(int street, int boulevard)
    {
        this.street = street;
        this.boulevard = boulevard;
    }

    // Getters

    public int getStreet()
    {
        return street;
    }

    public int getBoulevard()
    {
        return boulevard;
    }
}
