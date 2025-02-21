package RealEstate;

import RealEstate.Entities.Person;
import RealEstate.Entities.Role;
import RealEstate.Exceptions.InvalidChoice;

import java.io.*;
import java.util.ArrayList;

public class RealEstate {

    // Create a Singleton class for system
    private final static RealEstate realEstate = new RealEstate();
    //private Role user;
    private Person user;
    private ArrayList<Asset> listOfAssets;

    private ArrayList<Building> listOfBuildings;

    // Creating a private constructor

    private RealEstate()
    {
        readAssets();
        user = null;
    }

    // Singleton
    public static RealEstate getInstance()
    {
        return realEstate;
    }

    public static void setInstance(Person p)
    {
        user = p;
    }

    public ArrayList<Asset> getListOfAssets()
    {
        return listOfAssets;
    }

    /**
     * This function gets a street and a boulevard and adds a new asset if the location on the grid is available.
     * @param street - the street
     * @param boulevard - the boulevard
     * @return True if the given location is available. Otherwise, returns false.
     */
    public boolean addAsset(int street, int boulevard, double area, int price, boolean sold, ArrayList<Integer> innerApartments)
    {
        for (int i = 0; i < listOfAssets.size(); i++)
        {
            if (listOfAssets.get(i).getAddress().getStreet() == street && listOfAssets.get(i).getAddress().getBoulevard() == boulevard)
                return false;
        }
        Asset asset = new Asset(area, price, sold, street, boulevard, innerApartments);
        listOfAssets.add(asset);
        return true;
    }

    /**
     * This function reads all the existing assets from a given file and initialize an array of them.
     */
    public void readAssets()
    {
        ArrayList<Asset> readAssets = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Files/Assets.txt"));
            String line = reader.readLine();
            while (line != null)
            {
                String[] createAsset = line.split(",");
                double area = Double.parseDouble(createAsset[0]);
                int price = Integer.parseInt(createAsset[1]);
                boolean sold = Boolean.parseBoolean(createAsset[2]);
                int street = Integer.parseInt(createAsset[3]);
                int boulevard = Integer.parseInt(createAsset[4]);
                ArrayList<Integer> innerApartments = new ArrayList<>();
                for (int i = 5; i < createAsset.length; i++)
                    innerApartments.add(Integer.parseInt(createAsset[i]));

                Asset asset = new Asset(area, price, sold, street, boulevard, innerApartments);
                listOfAssets.add(asset);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public RealEstate.RealEstate.Asset getAsset()

    /**
     * This function shows all the existing assets in the terminal
     */
    public void ShowAssets()
    {
        if (listOfAssets.isEmpty())
            System.out.println("No assets to be shown");

        else
        {
            for (int i = 0; i < listOfAssets.size(); i++)
            {
                System.out.println(i+1 + ". " + listOfAssets.get(i).toString());
            }
        }
    }

    /**
     * This function gets an asset and adds it to the list of the existing assets.
     * @param asset - the asset that will be added.
     * @return - True if the operation succeeded. Otherwise, returns False.
     */
    public boolean addAssetToFile(Asset asset)
    {
        if (user.getRole() == Role.Agent)
        {
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("Files/Assets.txt"));
                //String[] addAsset = asset.toString().split(",");
                writer.write(asset.toString() + "\n"); // NEED TO CHECK HERE THE STRING
                writer.close();
                return true;
            } catch (IOException e){
                e.printStackTrace();
            }

            return false;
        }

        return false;
    }

    public boolean updateAsset(int i)
    {
        if (listOfAssets == null)
            throw new NullPointerException("No assets available!");

        System.out.println("Enter the new details MR,Price,Sold?,Street,Boulevard,Number of inner apartments");
        System.out.println("For example: 60,660000,true,2,3,3");


    }

    public boolean removeAsset(int i)
    {
        if (i > listOfAssets.size() || i < 1)
            throw new InvalidChoice("The given input is not in range!");

        // remove from list
        listOfAssets.remove(i-1);

        // remove from the file


    }



}
