package RealEstate;

import java.util.ArrayList;

public enum AdditionalService {
    Party("Party", 1000),
    Clean("Clean", 2500),
    Transfer("Transfer", 5000),
    Decorate("Decorate", 15000);

    private final String service;
    private final int cost;

    AdditionalService(String service, int cost)
    {
        this.service = service;
        this.cost = cost;
    }

    // Getters
    public String getService() {
        return service;
    }

    public int getCost() {
        return cost;
    }


    /**
     * This function gets a number and returns it's related service.
     * @param i - the number.
     * @return the matching service.
     */
    public static AdditionalService getServiceByInt(int i)
    {
        switch (i)
        {
            case 1: return AdditionalService.Party;
            case 2: return AdditionalService.Clean;
            case 3: return AdditionalService.Transfer;
            case 4: return AdditionalService.Decorate;
            default: return null;
        }
    }
}
