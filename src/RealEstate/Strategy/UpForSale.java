package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

/**
 * This class prints all the assets in range that their status is not sold.
 */
public class UpForSale extends Iterator implements IteratorStrategy {

    public UpForSale(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public double search()
    {
        double counter = 0;
        for (int i = 0; i < assets.size(); i++)
        {
            if (!assets.get(i).isSold())
            {
                counter++;
                System.out.println(assets.get(i).toString());
            }
        }

        if (counter == 0)
            System.out.println("There are no matching results!");

        return counter;
    }
}
