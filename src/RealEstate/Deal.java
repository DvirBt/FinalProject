package RealEstate;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Seller;

import java.util.ArrayList;

/**
 * This class represents a Deal which contains an asset, the total price of the deal and a list of all the additional services that the buyer required.
 */
public class Deal {

    private Asset asset;
    private int total_price;
    private ArrayList<AdditionalService> additionalServices;

    public Deal(Asset asset, ArrayList<AdditionalService> additionalServices)
    {
        this.asset = asset;
        total_price = asset.getPrice();
        this.additionalServices = additionalServices;

        for (int i = 0; i < this.additionalServices.size(); i++)
            total_price += this.additionalServices.get(i).getCost();
    }

    /**
     * This function collects all the data of this deal and returns a string of it.
     * @return a string of all the relevant information of this deal.
     */
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

        String line = "Buyer: " + asset.getOwner().toString() + "\nAgent: " + asset.getAgentOfAsset().toString() + "\nAsset: " + asset.toString() + "\nAdditional services: " + additionalService + "\nTotal price: " + total_price;

        return line;
    }
}

