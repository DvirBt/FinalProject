package RealEstate.Strategy.Iterators;

import RealEstate.Asset;

import java.util.ArrayList;

public interface IteratorStrategy {

    public void search(ArrayList<Asset> assets);

//    public ArrayList<Asset> AllIterators(Asset asset, int radius);
//    public ArrayList<Asset> AllSoldAssetsIterator(Asset asset, int radius);
//    public ArrayList<Asset> AllUpToSaleAssetsIterator(Asset asset, int radius);
//    public ArrayList<Asset> AllAbovePriceAssets(Asset asset, int radius);
//    public ArrayList<Asset> AllSamePriceAssets(Asset asset, int radius);
//    public ArrayList<Asset> AllBelowPriceAssets(Asset asset, int radius);

//    boolean hasNext();
//    Asset next();
}
