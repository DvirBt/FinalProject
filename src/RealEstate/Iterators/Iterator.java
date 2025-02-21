package RealEstate.Iterators;

import RealEstate.Asset;

import java.util.ArrayList;

public interface Iterator {

    public ArrayList<Asset> AveragePriceOfNearAssetsIterator(Asset asset, int radius);
    public ArrayList<Asset> AllSoldAssetsIterator(Asset asset, int radius);
    public ArrayList<Asset> AllUpToSaleAssetsIterator(Asset asset, int radius);
    public ArrayList<Asset> AllAbovePriceAssets(Asset asset, int radius);
    public ArrayList<Asset> AllSamePriceAssets(Asset asset, int radius);
    public ArrayList<Asset> AllBelowPriceAssets(Asset asset, int radius);
}
