package RealEstate.Tests;

import RealEstate.AdditionalService;
import RealEstate.Asset;
import RealEstate.Deal;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Role;
import RealEstate.Entities.Seller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealTest {

    Agent agent = new Agent("Dvir", "Ben Tovim");
    Seller seller = new Seller("Ori", "Amrani");
    Asset asset = new Asset(10, 10000, true, 1, 1, 1, agent, seller);
    @Test
    @DisplayName("Checks the output of a deal")
    void testToString()
    {
        ArrayList<AdditionalService> additionalServices = new ArrayList<>();
        Deal deal = new Deal(asset, additionalServices);
        String expected = "Buyer: Ori Amrani\n" + "Agent: Dvir Ben Tovim\n" + "Asset: Area: 10 | Price: 10000 | Sold?: true | Address: 1,1\n" + "Additional services: None\n" + "Total price: 10000";
        String result = deal.toString();
        assertEquals(expected, result);
    }
}