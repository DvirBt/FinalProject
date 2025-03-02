package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

/**
 * This class calculates all the assets average price and prints it.
 */
public class AveragePrice extends Iterator implements IteratorStrategy {

    public AveragePrice(ArrayList<Asset> assets, Asset asset, int radius)
    {
        super(assets, asset, radius);
    }

    @Override
    public double search()
    {
        double avg = 0;
        for (int i = 0; i < assets.size(); i++)
            avg += (double) (assets.get(i).getPrice() / assets.get(i).getArea());

        avg /= assets.size();
        System.out.println("The average price of the nearby assets is: " + avg + "$");
        return avg;
    }
}
