package RealEstate;

import java.util.ArrayList;
import java.util.HashSet;

public class Asset{

    // Properties

    private double area;
    private int price; // $
    private boolean sold; // True means this asset is sold, False otherwise.
    private Address address;
    private int innerApartments; // represents the number of inner apartments in an asset

    //private ArrayList<Integer> innerApartments; // represents the number of inner apartments in an asset (the rest of the address...)

    // Constructors

    public Asset(double area, int price, boolean sold, int street, int boulevard, int innerApartments)
    {
        this.area = area;
        this.price = price;
        this.sold = sold;
        this.address = new Address(street, boulevard);
        this.innerApartments = innerApartments;
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

    public int getInnerApartments()
    {
        return innerApartments;
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

    public void setInnerApartments(int innerApartments)
    {
        this.innerApartments = innerApartments;
    }
    public String toString()
    {
        return "Area: " + this.area + " | " + "Price: " + this.price + " | " +"Sold?: " + this.sold + " | " + "Address: " + address.getBoulevard() + "," + address.getStreet();
        //+ " | " + "Inner apartments: " + innerApartments;

    }

}
