package RealEstate;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Seller;

import java.util.ArrayList;

public class Deal {

    private Asset asset;
    private Buyer buyer;
    private Seller seller;
    private Agent agent;
    private int total_price;
    private ArrayList<AdditionalService> additionalServices;

    public Deal(Asset asset, Buyer buyer, Seller seller, Agent agent)
    {
        this.asset = asset;
        this.buyer = buyer;
        this.seller = seller;
        this.agent = agent;
        total_price = asset.getPrice();
        additionalServices = new ArrayList<>();
    }

    public void setTotal_price(int price)
    {
        total_price += price;
    }

    public ArrayList<AdditionalService> getAdditionalServices()
    {
        return additionalServices;
    }

    /**
     * This function checks if a service already exists in the deal
     * @param additionalService - the desired service
     * @return true if the service is already included in the deal. Otherwise, returns false.
     */
    public boolean isExist(AdditionalService additionalService)
    {
        if (this.additionalServices.contains(additionalService))
            return true;

        System.out.println("This service is already included in the deal!");
        return false;
    }

    /**
     * This function gets a String and returns all the services to the deal.
     * @param line - a String contains all the chosen services.
     * @return an ArrayList of all the services.
     */
    public ArrayList<AdditionalService> getServiceByString(String line)
    {
        String[] input = line.split(",");
        ArrayList<AdditionalService> services = new ArrayList<>();
        for (int i = 0; i < input.length; i++)
        {
            int number = Integer.parseInt(input[i]);
            AdditionalService service = AdditionalService.getServiceByInt(number);
            if(!isExist(service))
                services.add(service);
        }

        return services;
    }
}

