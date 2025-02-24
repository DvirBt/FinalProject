package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class EqualPrice extends Iterator implements IteratorStrategy {

    public EqualPrice(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public void search()
    {
        double avgPrice = asset.getPrice() / asset.getArea();
        for (int i = 0; i < assets.size(); i++)
        {
            double currentAvgPrice = assets.get(i).getPrice() / assets.get(i).getArea();
            if (currentAvgPrice == avgPrice)
                System.out.println(assets.get(i).toString());
        }
    }
}
