package RealEstate;

import java.util.ArrayList;

public class Building {

    private ArrayList<Asset> assets;
    private Address address;

    public Building(Address address)
    {
        assets = new ArrayList<>();
        this.address = address;
    }

    public boolean addAsset(Asset asset)
    {
        if (asset == null)
            throw new NullPointerException("RealEstate.RealEstate.Asset is null!");

        assets.add(asset);
        return true;
    }
}
