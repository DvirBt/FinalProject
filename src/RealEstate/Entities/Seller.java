package RealEstate.Entities;

import RealEstate.Asset;

public class Seller extends Person{

    public Seller(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Seller);
    }

    public void sendMessage(String message)
    {

    }

    // Should be in RealEstate? and check the status of the user -> authorized
    public void deleteAsset(Asset asset)
    {

    }
}
