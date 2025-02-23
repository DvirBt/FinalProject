package RealEstate;

import java.util.ArrayList;

public class Building {

    private ArrayList<Asset> assetsList;
    private Address address;

    public Building(Asset asset)
    {
        assetsList = new ArrayList<>();
        this.address = new Address(address.getStreet(), address.getBoulevard());
    }

    public ArrayList<Asset> getAssetsList()
    {
        return assetsList;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * This function adds an asset to the building.
     * @param asset - the asset that will be added to the building.
     */
    public void addAsset(Asset asset)
    {
        if (asset == null)
            throw new NullPointerException("Asset is null!");

        assetsList.add(asset);
    }

    /**
     * This function gets an asset and check if it's in this building.
     * @param asset - the given asset.
     * @return true if the asset in this building. Otherwise returns false.
     */
    public boolean isExist(Asset asset)
    {
        if (assetsList.contains(asset))
            return true;

        return false;
    }

    public void removeAsset(Asset asset)
    {
        assetsList.remove(asset);
    }

    public String toString()
    {
        String s = "";
        String fullAddress = "";
        for (int i = 0; i < assetsList.size(); i++)
        {
            fullAddress += String.valueOf(assetsList.get(i).getInnerApartments()); // convert int to String
            s += assetsList.get(i).toString() + fullAddress + "\n";
        }

        return s;
    }
}
