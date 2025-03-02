package RealEstate.Tests;

import RealEstate.Asset;
import RealEstate.Building;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Seller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest{

    Agent agent = new Agent("Dvir", "Ben Tovim");
    Seller seller = new Seller("Ori", "Amrani");
    Asset asset = new Asset(10, 10000, true, 1, 1, 1, agent, seller);
    Asset asset2 = new Asset(1000, 50000, false, 2, 0, 0, agent, seller);
    Building building = new Building(asset);

    @Test
    @DisplayName("This test checks if a given asset exists in this building")
    void isExist()
    {
        assertFalse(building.isExist(asset2)); // asset 2 not in this building
        assertTrue(building.isExist(asset)); // asset is in this building
        building.add(asset2); // add asset2
        assertTrue(building.isExist(asset2)); // asset2 now in this building
        building.remove(asset2); // remove asset2
        assertFalse(building.isExist(asset2)); // asset2 now not in this building
    }

    @Test
    @DisplayName("This test checks the building's toString properties")
    void testToString()
    {
        String expected = "Area: 10 | Price: 10000 | Sold?: true | Address: 1,1,1\n";
        String result = building.toString();
        assertEquals(expected, result);
    }
}