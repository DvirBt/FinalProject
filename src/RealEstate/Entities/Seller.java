package RealEstate.Entities;

import RealEstate.Asset;

public class Seller extends Person{

    public Seller(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Seller);
    }
}
