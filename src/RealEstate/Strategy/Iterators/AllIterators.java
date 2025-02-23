package RealEstate.Strategy.Iterators;

import RealEstate.Asset;

import java.util.ArrayList;

public class AllIterators implements NearAssets{

    private ArrayList<Asset> assets;
    private int radius;
    public AllIterators(ArrayList<Asset> assets, Asset asset, int radius)
    {
        this.radius = radius;
        this.assets = new ArrayList<>();
        // check which assets are in the circle
        for (int i = 0; i < assets.size(); i++)
        {
            Asset currentAsset = assets.get(i);
            int x = asset.getAddress().getStreet() - currentAsset.getAddress().getStreet();
            int y = asset.getAddress().getBoulevard() - currentAsset.getAddress().getBoulevard();

            // if in the circle
            if ((Math.pow(x,2) + Math.pow(y,2) <= Math.pow(radius, 2)))
                this.assets.add(currentAsset);
        }
    }

    public IteratorStrategy AveragePriceOfNearAssetsIterator()
    {
        return average();
    }

    public IteratorStrategy AllSoldAssetsIterator()
    {
        return sold();
    }

    public IteratorStrategy AllUpToSaleAssetsIterator()
    {
        return sale();
    }

    public IteratorStrategy AllAbovePriceAssetsIterator()
    {
        return above();
    }

    public IteratorStrategy AllSamePriceAssetsIterator()
    {
        return equal();
    }

    public IteratorStrategy AllBelowPriceAssetsIterator()
    {
        return below();
    }

    private class average implements IteratorStrategy {

        double average = 0;
        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {
            if (this.hasNext())
                return assets.get(index++);

            return null;
        }
    }

    private class sold implements IteratorStrategy {

        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {

        }
    }

    private class sale implements IteratorStrategy {

        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {

        }
    }

    private class above implements IteratorStrategy {

        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {

        }
    }

    private class equal implements IteratorStrategy {

        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {

        }
    }

    private class below implements IteratorStrategy {

        int index = 0;
        @Override
        public boolean hasNext()
        {
            if (index < assets.size())
                return true;

            return false;
        }

        @Override
        public Asset next()
        {

        }
    }
}
