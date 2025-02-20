import java.util.ArrayList;

public class Asset{

    // Properties

    private double area;
    private int price; // $
    private boolean sold; // True means this asset is sold, False otherwise.
    private Address address;

    private ArrayList<Apartment> apartmentsList = null;

    // Constructors

    public Asset(double area, int price, boolean sold, Address address)
    {
        this.area = area;
        this.price = price;
        this.sold = sold;
        this.address = new Address(address.getStreet(), address.getBoulevard());
    }

    public Asset(double area, int price, boolean sold, int street, int boulevard)
    {
        this.area = area;
        this.price = price;
        this.sold = sold;
        this.address = new Address(street, boulevard);
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

    public boolean addApartment(Apartment apartment)
    {
        if (apartment == null)
            throw new NullPointerException("Apartment is null!");

        apartmentsList.add(apartment);
        return true;
    }

    public String toString()
    {
        return "Area: " + this.area + " | " + "Price: " + this.price + " | " +"Sold?: " + this.sold + " | " + "Address: " + address.getBoulevard() + "," + address.getStreet();
    }

}
