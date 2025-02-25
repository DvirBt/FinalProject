package RealEstate;

import java.util.ArrayList;

/**
 * This class represents a Building - which contains a list of apartments, address and if this building is divided or not (private building or an actual one).
 * This class also uses the Composite design pattern.
 */
public class Building {

    private ArrayList<Asset> assetsList;
    private Address address;
    private boolean divided;

    public Building(Asset asset)
    {
        assetsList = new ArrayList<>();
        assetsList.add(asset);
        this.address = new Address(asset.getAddress().getStreet(), asset.getAddress().getBoulevard());
        if (asset.getInnerApartments() != 0)
            divided = false;
        divided = true;
    }

    public ArrayList<Asset> getAssetsList()
    {
        return assetsList;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isDivided()
    {
        return divided;
    }

    /**
     * This function adds an asset to the building.
     * @param asset - the asset that will be added to the building.
     */
    public void add(Asset asset)
    {
        if (asset == null)
            throw new NullPointerException("Asset is null!");

        assetsList.add(asset);
    }

    /**
     * This function gets an asset and check if it's in this building.
     * @param asset - the given asset.
     * @return true if the asset in this building. Otherwise, returns false.
     */
    public boolean isExist(Asset asset)
    {
        if (assetsList.contains(asset))
            return true;

        return false;
    }

    /**
     * This function removes a given asset.
     * @param asset a given asset
     */
    public void remove(Asset asset)
    {
        assetsList.remove(asset);
    }

    /**
     * This function returns the details of each apartment in the building.
     * @return a string with all the details of the apartments.
     */
    public String toString()
    {

        if (!divided)
            assetsList.getFirst().toString();

        String s = "";
        String fullAddress = "";
        for (int i = 0; i < assetsList.size(); i++)
        {
            fullAddress += "," + String.valueOf(assetsList.get(i).getInnerApartments()); // convert int to String
            s += assetsList.get(i).toString() + fullAddress + "\n";
        }

        return s;
    }
}
