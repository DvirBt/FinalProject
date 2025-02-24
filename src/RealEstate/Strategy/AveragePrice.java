package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class AveragePrice extends Iterator implements IteratorStrategy {

    public AveragePrice(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public void search()
    {
        double avg = 0;
        for (int i = 0; i < assets.size(); i++)
            avg += assets.get(i).getPrice();

        System.out.println("The average price of the nearby assets is: " + avg / assets.size() + "$");
    }
}
