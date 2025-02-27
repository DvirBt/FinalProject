package RealEstate.Tests;

import RealEstate.Asset;
import RealEstate.Building;
import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;

/**
 * This class will contain many data members instead of redefining them every class.
 * Each uniTest will inherit from this class.
 */
public class BaseStats {
    Agent agent = new Agent("Dvir", "Ben Tovim");
    Buyer buyer = new Buyer("Ori", "Amrani");
    Asset asset = new Asset(10, 10000, true, 1, 1, 1);
    Asset asset2 = new Asset(1000, 50000, false, 2, 0, 0);
    Building building = new Building(asset);
}
