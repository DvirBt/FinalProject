package RealEstate.Entities;

public enum Role {
    Agent(1),
    Buyer(2),
    Seller(3),
    Exit(4);
    final int role;

    Role(int role)
    {
        this.role = role;
    }

    /**
     * This functions gets an integer and returns it's related Role.
     * @param i - the integer
     * @return - the role.
     */
    public static Role SelectRoleByInteger(int i)
    {
        switch (i)
        {
            case 1: return Role.Agent;
            case 2: return Role.Buyer;
            case 3: return Role.Seller;
            case 4: return Role.Exit;
            default:
        }

        return null;
    }
}
