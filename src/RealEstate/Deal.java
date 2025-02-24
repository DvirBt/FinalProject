package RealEstate;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Seller;

import java.util.ArrayList;

public class Deal {

    private Asset asset;
    private Buyer buyer;
    //private Seller seller;
    private Agent agent;
    private int total_price;
    private ArrayList<AdditionalService> additionalServices;

    public Deal(Asset asset, Buyer buyer, Agent agent, ArrayList<AdditionalService> additionalServices) // Seller seller,
    {
        this.asset = asset;
        this.buyer = buyer;
        //this.seller = seller;
        this.agent = agent;
        total_price = asset.getPrice();
        this.additionalServices = additionalServices;

        for (int i = 0; i < this.additionalServices.size(); i++)
            total_price += this.additionalServices.get(i).getCost();
    }


    public ArrayList<AdditionalService> getAdditionalServices()
    {
        return additionalServices;
    }

    public String toString()
    {
        String additionalService = "";
        if (additionalServices.isEmpty())
            additionalService = "None";
        else
        {
            for (int i = 0; i < additionalServices.size()-1; i++)
                additionalService += additionalServices.get(i).getServiceType() + ", ";

            additionalService += additionalServices.getLast().getServiceType();
        }

        String line = "Buyer: " + buyer.toString() + "\nAgent: " + agent.toString() + "\nAsset: " + asset.toString() + "\nAdditional services: " + additionalService + "\nTotal price: " + total_price;

        return line;
    }
}

