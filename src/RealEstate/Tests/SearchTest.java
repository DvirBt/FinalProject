package RealEstate.Tests;

import RealEstate.Asset;
import RealEstate.Building;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Seller;
import RealEstate.Strategy.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    Agent agent = new Agent("Dvir", "Ben Tovim");
    Seller seller = new Seller("Ori", "Amrani");
    Search search;

    // radius 0
    Asset asset1 = new Asset(10, 10000, true, 1, 1, 1, agent, seller);
    Asset asset2 = new Asset(20, 20000, false, 1, 1, 1, agent, seller); // equal to asset
    Asset asset3 = new Asset(10, 20000, true, 1, 1, 1, agent, seller); // above price + sold
    Asset asset4 = new Asset(10, 5000, true, 1, 1, 1, agent, seller); // below price

    // radius 1
    Asset asset5 = new Asset(50, 1000000, false, 2, 1, 1, agent, seller); // raidus 1
    @Test
    void executeStrategy()
    {
        ArrayList<Asset> listOfAssets = new ArrayList<>();
        listOfAssets.add(asset1);
        listOfAssets.add(asset2);
        listOfAssets.add(asset3);
        listOfAssets.add(asset4);
        listOfAssets.add(asset5);


        // center - asset1, radius - 0
        search = new Search(new AveragePrice(listOfAssets, asset1, 0));
        double avgPrice = (double) (asset1.getPrice()/asset1.getArea() + asset2.getPrice()/asset2.getArea() + asset3.getPrice()/asset3.getArea() + asset4.getPrice()/asset4.getArea());
        avgPrice /= 4; // the size of the list without the asset in radius 1
        assertEquals(avgPrice, search.executeStrategy());
        //System.out.println("The actual calculated average: " + avgPrice + "$");

        search = new Search(new SoldAssets(listOfAssets, asset1, 0));
        assertEquals(3, search.executeStrategy()); // asset 1,3,4 are sold

        search = new Search(new UpForSale(listOfAssets, asset1, 0));
        assertEquals(1, search.executeStrategy()); // only asset2 is for sale

        search = new Search(new AbovePrice(listOfAssets, asset1, 0));
        assertEquals(1, search.executeStrategy()); // only asset3 is above the price

        search = new Search(new BelowPrice(listOfAssets, asset1, 0));
        assertEquals(1, search.executeStrategy()); // only asset4 is below

        search = new Search(new EqualPrice(listOfAssets, asset1, 0));
        assertEquals(2, search.executeStrategy()); // assets 1,2 are proportional

        // center - asset1, radius - 1
        search = new Search(new AveragePrice(listOfAssets, asset1, 1));
        avgPrice = (double) (asset1.getPrice()/asset1.getArea() + asset2.getPrice()/asset2.getArea() + asset3.getPrice()/asset3.getArea() + asset4.getPrice()/asset4.getArea() + asset5.getPrice()/asset5.getArea());
        avgPrice /= listOfAssets.size();
        assertEquals(avgPrice, search.executeStrategy());
        //System.out.println("The actual calculated average: " + avgPrice + "$");

        search = new Search(new SoldAssets(listOfAssets, asset1, 1));
        assertEquals(3, search.executeStrategy()); // asset 1,3,4 are sold

        search = new Search(new UpForSale(listOfAssets, asset1, 1));
        assertEquals(2, search.executeStrategy()); // assets 2,5 are up for sale

        search = new Search(new AbovePrice(listOfAssets, asset1, 1));
        assertEquals(2, search.executeStrategy()); // assets 3,5 are above

        search = new Search(new BelowPrice(listOfAssets, asset1, 1));
        assertEquals(1, search.executeStrategy()); // only asset4 is below

        search = new Search(new EqualPrice(listOfAssets, asset1, 1));
        assertEquals(2, search.executeStrategy()); // assets 1,2 are proportional
    }
}