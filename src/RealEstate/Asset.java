package RealEstate;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Person;
import RealEstate.Entities.Seller;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class represents an Asset which contains - it's area, price, status (sold or not), address, inner apartments (if one contains more apartments in it),
 * an agent who can edit this asset properties and an owner of the asset.
 */
public class Asset{

    private int area;
    private int price; // $
    private boolean sold; // True means this asset is sold, False otherwise.
    private Address address;
    private int innerApartments; // represents the number of inner apartments in an asset
    // NEW
    private Agent agentOfAsset;
    private Person owner;
    //


//    public Asset(int area, int price, boolean sold, int street, int boulevard, int innerApartments)
//    {
//        this.area = area;
//        this.price = price;
//        this.sold = sold;
//        this.address = new Address(street, boulevard);
//        this.innerApartments = innerApartments;
//    }
    // new constructor

    public Asset(int area, int price, boolean sold, int street, int boulevard, int innerApartments, Agent agentOfAsset, Person owner)
    {
        this.area = area;
        this.price = price;
        this.sold = sold;
        this.address = new Address(street, boulevard);
        this.innerApartments = innerApartments;
        this.agentOfAsset = agentOfAsset;
        this.owner = owner;
    }

    //

    // Getters and Setters
    public int getArea() {
        return area;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public Address getAddress() {
        return address;
    }

    public int getInnerApartments()
    {
        return innerApartments;
    }

    public Agent getAgentOfAsset()
    {
        return agentOfAsset;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setAddress(int street, int boulevard) {
        this.address.setStreet(street);
        this.address.setBoulevard(boulevard);
    }

    public void setInnerApartments(int innerApartments)
    {
        this.innerApartments = innerApartments;
    }

    public void setAgentOfAsset(Agent agentOfAsset)
    {
        this.agentOfAsset = agentOfAsset;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    /**
     * This function collects all the details of this asset and returns it as a string.
     * @return a string with all the data.
     */
    public String toString()
    {
        return "Area: " + this.area + " | " + "Price: " + this.price + " | " +"Sold?: " + this.sold + " | " + "Address: " + address.getBoulevard() + "," + address.getStreet();
        //+ " | " + "Inner apartments: " + innerApartments;

    }

    /**
     * This function returns all the relevant data of this asset - in order to update the file.
     * @return a string with all the relevant data to insert to the file.
     */
    public String getValues()
    {
        // area+","+price+","+sold+","+ address.getBoulevard()+","+ address.getStreet()+","+innerApartments;
        return agentOfAsset.getId()+","+owner.getId()+","+area+","+price+","+sold+","+ address.getBoulevard()+","+ address.getStreet()+","+innerApartments;
    }

}
