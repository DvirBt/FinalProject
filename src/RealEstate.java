import java.io.PrintStream;
import java.util.ArrayList;

public class RealEstate {

    // Create a Singleton class for system
    private final static RealEstate system = null;

    private ArrayList<Asset> assets;

    // Creating a private constructor

    private RealEstate()
    {

    }

    // Create a new instance
    public static RealEstate getInstance()
    {

    }


    /**
     * This function gets a street and a boulevard and adds a new asset if the location on the grid is available.
     * @param street - the street
     * @param boulevard - the boulevard
     * @return True if the given location is available. Otherwise, returns false.
     */
    public boolean addAsset(int street, int boulevard, double area, int price, boolean sold)
    {
        for (int i = 0; i < assets.size(); i++)
        {
            if (assets.get(i).getAddress().getStreet() == street && assets.get(i).getAddress().getBoulevard() == boulevard)
                return false;
        }
        Asset asset = new Asset(area, price, sold, street, boulevard);
        assets.add(asset);
        return true;
    }

    public void ShowAssets()
    {
        if (assets.isEmpty())
            System.out.println("No assets to be shown");

        else
        {
            for (int i = 0; i < assets.size(); i++)
            {
                System.out.println(i+1 + ". " + assets.get(i).toString());
            }
        }
    }

}
