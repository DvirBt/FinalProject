package RealEstate;

/**
 * This Enum represents an Additional Service which is a service that is offered by an agent to a buyer
 * when a deal is on the process. This Enum contains a name and the cost of the service.
 */
public enum AdditionalService {
    Guarantee("Guarantee", 1000),
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
    public String getServiceType() {
        return service;
    }

    public int getCost() {
        return cost;
    }
}
