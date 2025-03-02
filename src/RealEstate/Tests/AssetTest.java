package RealEstate.Tests;

import RealEstate.Asset;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Seller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssetTest{
    Agent agent = new Agent("Dvir", "Ben Tovim");
    Seller seller = new Seller("Ori", "Amrani");
    Asset asset = new Asset(10, 10000, true, 1, 1, 1, agent, seller);
    Asset asset2 = new Asset(1000, 50000, false, 2, 0, 0, agent, seller);

    @Test
    @DisplayName("This test checks the asset's toString properties")
    void testToString()
    {
        String expected = "Area: 10 | Price: 10000 | Sold?: true | Address: 1,1";
        String result = asset.toString();
        assertEquals(expected, result);
        expected = "Area: 1000 | Price: 50000 | Sold?: false | Address: 0,2";
        result = asset2.toString();
        assertEquals(expected, result);
    }
}