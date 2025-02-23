package RealEstate.Entities;

import RealEstate.Asset;

import java.util.ArrayList;

public class Buyer extends Person{

    private ArrayList<Asset> assets;

    public Buyer(String firstName, String lastName)
    {
        super(firstName, lastName, Role.Buyer);
        assets = new ArrayList<>();

    }

    public void buyAsset(Asset asset)
    {
        if (asset == null)
            throw new NullPointerException("Asset is null!");

        assets.add(asset);

        // CONTINUE
    }
}
