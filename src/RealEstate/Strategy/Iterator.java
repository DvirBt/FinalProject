package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

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

            // if in the circle
            if ((Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2)))
                this.assets.add(currentAsset);
        }
    }
}



