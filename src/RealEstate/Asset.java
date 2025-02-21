package RealEstate;

import java.util.ArrayList;

public class Asset{

    // Properties

    private double area;
    private int price; // $
    private boolean sold; // True means this asset is sold, False otherwise.
    private Address address;
    //private int innerApartments; // represents the number of inner apartments in an asset

    private ArrayList<Integer> innerApartments; // represents the number of inner apartments in an asset

    // Constructors

    public Asset(double area, int price, boolean sold, int street, int boulevard, ArrayList<Integer> innerApartments)
    {
        this.area = area;
        this.price = price;
        this.sold = sold;
        this.address = new Address(street, boulevard);
        this.innerApartments = new ArrayList<>();
    }

    // Getters and Setters
    public double getArea() {
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

    public void setArea(double area) {
        this.area = area;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setInnerApartments(ArrayList<Integer> innerApartments) {
        this.innerApartments = innerApartments;
    }

    public ArrayList<Integer> getInnerApartments() {
        return innerApartments;
    }

    public String toString()
    {
        return "Area: " + this.area + " | " + "Price: " + this.price + " | " +"Sold?: " + this.sold + " | " + "RealEstate.RealEstate.Address: " + address.getBoulevard() + "," + address.getStreet();
        // maybe add the rest of the address?
    }

}
