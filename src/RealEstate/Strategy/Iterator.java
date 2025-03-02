package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

/**
 * This class calculates and initialize all the relevant assets based on a given asset as a center point of a circle and a given radius.
 * In order to check if an asset is inside the circle I use the formula - (x-a)^2 + (y-b)^2 = r^2.
 * x and y are the given asset's street and boulevard => (x,y) = center of the circle.
 */
public abstract class Iterator {

    protected final ArrayList<Asset> assets;
    protected final Asset asset;
    protected final int radius;

    public Iterator(ArrayList<Asset> assets, Asset asset, int radius)
    {
        this.radius = radius;
        this.asset = asset;
        this.assets = new ArrayList<>();
        // check which assets are in the circle
        for (int i = 0; i < assets.size(); i++) {
            Asset currentAsset = assets.get(i);
            int x = asset.getAddress().getStreet() - currentAsset.getAddress().getStreet();
            int y = asset.getAddress().getBoulevard() - currentAsset.getAddress().getBoulevard();

            // if the current asset is in the circle
            if ((Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2)))
                this.assets.add(currentAsset);
        }
    }
}



