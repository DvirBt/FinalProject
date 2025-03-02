package RealEstate;

/**
 * This class represents an address of an asset as a point in the grid.
 */
public class Address {

    // Properties
    private int street;
    private int boulevard;

    public Address(int street, int boulevard)
    {
        this.street = street;
        this.boulevard = boulevard;
    }

    public int getStreet()
    {
        return street;
    }
    public int getBoulevard()
    {
        return boulevard;
    }

    public void setStreet(int street) {
        this.street = street;
    }

    public void setBoulevard(int boulevard) {
        this.boulevard = boulevard;
    }
}
