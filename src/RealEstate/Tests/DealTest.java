package RealEstate.Tests;

import RealEstate.AdditionalService;
import RealEstate.Asset;
import RealEstate.Deal;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealTest extends BaseStats {

    @Test
    @DisplayName("Checks the output of a deal")
    void testToString()
    {
        ArrayList<AdditionalService> additionalServices = new ArrayList<>();
        Deal deal = new Deal(asset, buyer, agent, additionalServices);
        String expected = "Buyer: Ori Amrani\n" + "Agent: Dvir Ben Tovim\n" + "Asset: Area: 10 | Price: 10000 | Sold?: true | Address: 1,1\n" + "Additional services: None\n" + "Total price: 10000";
        //assertEquals(expected, deal.toString());
        String result = deal.toString();
        assertEquals(expected, result);
    }
}