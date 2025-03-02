package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

/**
 * This class prints all the assets that their average price is below the given asset's average price.
 */
public class BelowPrice extends Iterator implements IteratorStrategy {

    public BelowPrice(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public double search()
    {
        double counter = 0;
        double avgPrice = (double) asset.getPrice() / asset.getArea();
        for (int i = 0; i < assets.size(); i++)
        {
            double currentAvgPrice = (double) assets.get(i).getPrice() / assets.get(i).getArea();
            if (currentAvgPrice < avgPrice)
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