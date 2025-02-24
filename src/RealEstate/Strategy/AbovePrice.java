package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class AbovePrice extends Iterator implements IteratorStrategy {

    public AbovePrice(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public void search()
    {
        boolean found = false;
        double avgPrice = asset.getPrice() / asset.getArea();
        for (int i = 0; i < assets.size(); i++) {
            double currentAvgPrice = assets.get(i).getPrice() / assets.get(i).getArea();
            if (currentAvgPrice > avgPrice)
            {
                found = true;
                System.out.println(assets.get(i).toString());
            }
        }

        if (!found)
            System.out.println("There are no matching results!");
    }
}
