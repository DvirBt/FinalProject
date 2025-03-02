package RealEstate.Entities;

/**
 * This Enum represents a Role by a number.
 */
public enum Role {
    Agent(1),
    Buyer(2),
    Seller(3);
    private final int role;

    Role(int role)
    {
        this.role = role;
    }
}
