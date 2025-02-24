package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class SoldAssets extends Iterator implements IteratorStrategy {

    public SoldAssets(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public void search()
    {
        boolean found = false;
        for (int i = 0; i < assets.size(); i++)
        {
            if (assets.get(i).isSold())
            {
                found = true;
                System.out.println(assets.get(i).toString());
            }
        }

        if (!found)
            System.out.println("There are no matching results!");
    }
}
