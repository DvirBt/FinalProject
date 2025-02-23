package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class Iterator {

    private final ArrayList<Asset> assets;
    private final Asset asset;
    private final int radius;

    public Iterator(ArrayList<Asset> assets, Asset asset, int radius) {
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

    private class AveragePrice implements IteratorStrategy {

        @Override
        public ArrayList<Asset> search()
        {
            double avg = 0;
            for (int i = 0; i < assets.size(); i++)
                avg += assets.get(i).getPrice();

            System.out.println("The average price of the nearby assets is: " + avg / assets.size());
            return null;
        }
    }

    private class SoldAssets implements IteratorStrategy {

        @Override
        public ArrayList<Asset> search()
        {
            ArrayList<Asset> returnAssets = new ArrayList<>();
            for (int i = 0; i < assets.size(); i++) {
                if (assets.get(i).isSold())
                    returnAssets.add(assets.get(i));
            }

            return returnAssets;
        }
    }

    private class UpForSale implements IteratorStrategy {

        @Override
        public ArrayList<Asset> search()
        {
            ArrayList<Asset> returnAssets = new ArrayList<>();
            for (int i = 0; i < assets.size(); i++) {
                if (!assets.get(i).isSold())
                    returnAssets.add(assets.get(i));
            }

            return returnAssets;
        }
    }

    private class AbovePrice implements IteratorStrategy {

        @Override
        public ArrayList<Asset> search()
        {
            ArrayList<Asset> returnAssets = new ArrayList<>();
            double avgPrice = asset.getPrice() / asset.getArea();
            for (int i = 0; i < assets.size(); i++) {
                double currentAvgPrice = assets.get(i).getPrice() / assets.get(i).getArea();
                if (currentAvgPrice > avgPrice)
                    returnAssets.add(assets.get(i));
            }

            return returnAssets;
        }

        private class EqualPrice implements IteratorStrategy {

            @Override
            public ArrayList<Asset> search()
            {
                ArrayList<Asset> returnAssets = new ArrayList<>();
                double avgPrice = asset.getPrice() / asset.getArea();
                for (int i = 0; i < assets.size(); i++) {
                    double currentAvgPrice = assets.get(i).getPrice() / assets.get(i).getArea();
                    if (currentAvgPrice == avgPrice)
                        returnAssets.add(assets.get(i));
                }

                return returnAssets;
            }
        }

        private class BelowPrice implements IteratorStrategy {

            @Override
            public ArrayList<Asset> search()
            {
                ArrayList<Asset> returnAssets = new ArrayList<>();
                double avgPrice = asset.getPrice() / asset.getArea();
                for (int i = 0; i < assets.size(); i++) {
                    double currentAvgPrice = assets.get(i).getPrice() / assets.get(i).getArea();
                    if (currentAvgPrice < avgPrice)
                        returnAssets.add(assets.get(i));
                }

                return returnAssets;
            }
        }
    }
}


