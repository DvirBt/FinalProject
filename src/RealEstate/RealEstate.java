package RealEstate;

import RealEstate.Entities.*;
import RealEstate.Exceptions.InvalidChoice;
import RealEstate.Exceptions.UnAuthorizedUser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RealEstate {

    // Create a Singleton class for system
    private static final RealEstate realEstate = new RealEstate();
    //private Role user;
    private Person user;
    private ArrayList<Asset> listOfAssets;
    Scanner reader = new Scanner(System.in);
    private ArrayList<Building> listOfBuildings;

    private ArrayList<Agent> agents;
    private ArrayList<Buyer> buyers;
    private ArrayList<Seller> sellers;


    // Creating a private constructor

    private RealEstate()
    {
        initAssetsList();
        initBuildingsList();
        initUsers();
        // switch and display options by role OR to general
    }

    // Singleton
    public static RealEstate getInstance()
    {
        return realEstate;
    }

    public ArrayList<Asset> getListOfAssets()
    {
        return listOfAssets;
    }

    public void login()
    {
        boolean found = false;
        while (!found)
        {
            System.out.println("Enter first name");
            String firstName = reader.nextLine();
            System.out.println("Enter last name");
            String lastName = reader.nextLine();
            System.out.println("Login as:\n1. Agent\n2. Buyer\n 3. Seller");
            int choice = makeChoice();
            switch (choice)
            {
                case 1:
                    for (int i = 0; i < agents.size(); i++)
                    {
                        if (agents.get(i).samePerson(firstName, lastName))
                        {
                            found = true;
                            user = agents.get(i);
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < buyers.size(); i++)
                    {
                        if (buyers.get(i).samePerson(firstName, lastName))
                        {
                            found = true;
                            user = buyers.get(i);
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < sellers.size(); i++)
                    {
                        if (sellers.get(i).samePerson(firstName, lastName))
                        {
                            found = true;
                            user = sellers.get(i);
                            break;
                        }
                    }
                    break;

                default:
                    System.out.println("Login failed, try again.");
            }
        }

        System.out.println("Welcome back " + user.getFirstName() + user.getLastName());
    }

    private int makeChoice()
    {
        // create a Scanner to read the inputs
        Scanner reader = new Scanner(System.in);
        int input = reader.nextInt();
        while (true)
        {
            try
            {
                if (input < 4 && input > 0)
                    return input;

                throw new InvalidChoice("The given input is invalid!\nPlease enter a number between 1-3");
            }
            catch (InvalidChoice e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showActions()
    {
        System.out.println("Available actions:\n1. See assets\n2. Edit assets\n3. Remove asset\n4. Notify agent\n5. Pull assets\n6. Close a deal\n9. Exist");

    }

    public void initUsers()
    {
        agents = new ArrayList<>();
        buyers = new ArrayList<>();
        sellers = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("Files/Users.txt"));
            String line = reader.readLine();
            while (line != null)
            {
                Person p = readUser(line);
                if (p instanceof Agent)
                    agents.add((Agent) p);
                else if (p instanceof Buyer)
                    buyers.add((Buyer) p);
                else // seller
                    sellers.add((Seller) p);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This functions gets a string as a person input and adds it to the relevant list.
     * @param line - the given string.
     */
    private Person readUser(String line)
    {
        if (line == null)
            throw new NullPointerException("Line is null!");

        // maybe add try
        String[] createAsset = line.split(",");
        String firstName = createAsset[0];
        String lastName = createAsset[1];
        int role = Integer.parseInt(createAsset[2]);
        UserFactory userFactory = new UserFactory();
        return userFactory.createUserType(firstName, lastName, role);
    }

    /**
     * This function checks if a given string of person data is a registered user.
     * @param line - the given data.
     * @return true if the person exists in the file. Otherwise returns false.
     */
    public boolean tryLogin(String line)
    {
        Person p = readUser(line);
        if (!agents.contains(p) && !buyers.contains(p) && !sellers.contains(p))
            return false;

        return true;
    }

    /**
     * This function reads all assets from the file and initializes the assets list.
     */
    private void initAssetsList()
    {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Files/Assets.txt"));
            String line = reader.readLine();
            while (line != null)
            {
                Asset asset = readAsset(line);
                if (asset != null)
                    listOfAssets.add(asset);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function initialize the buildings.
     */
    private void initBuildingsList()
    {
        listOfBuildings = new ArrayList<>();
        boolean exist;
        for (int i = 0; i < listOfAssets.size(); i++)
        {
            Asset currentAsset = listOfAssets.get(i);
            exist = false;
            for (int j = 0; j < listOfBuildings.size(); j++)
            {
                Building currentBuilding = listOfBuildings.get(j);
                if (currentAsset.getAddress().equals(currentBuilding.getAddress()))
                {
                    currentBuilding.addAsset(currentAsset);
                    exist = true;
                    break;
                }
            }

            if (!exist)
            {
                Building building = new Building(currentAsset);
            }
        }
    }

//    /**
//     * This function gets a street and a boulevard and adds a new asset if the location on the grid is available.
//     * @param street - the street
//     * @param boulevard - the boulevard
//     * @return True if the given location is available. Otherwise, returns false.
//     */
//    private boolean addAsset(int street, int boulevard, double area, int price, boolean sold, ArrayList<Integer> innerApartments)
//    {
//        for (int i = 0; i < listOfAssets.size(); i++)
//        {
//            if (listOfAssets.get(i).getAddress().getStreet() == street && listOfAssets.get(i).getAddress().getBoulevard() == boulevard)
//                return false;
//        }
//        Asset asset = new Asset(area, price, sold, street, boulevard, innerApartments);
//        listOfAssets.add(asset);
//        return true;
//    }

    /**
     * This functions gets a String as an asset input and transfers it to an asset object.
     * @param line - the given input.
     * @return the asset if the input is valid. Otherwise, returns null.
     */
    private Asset readAsset(String line)
    {
        if (line == null)
            throw new NullPointerException("Line is null!");

        // maybe add try
        String[] createAsset = line.split(",");
        double area = Double.parseDouble(createAsset[0]);
        int price = Integer.parseInt(createAsset[1]);
        boolean sold = Boolean.parseBoolean(createAsset[2]);
        int street = Integer.parseInt(createAsset[3]);
        int boulevard = Integer.parseInt(createAsset[4]);
        //ArrayList<Integer> innerApartments = new ArrayList<>();
//        for (int i = 5; i < createAsset.length; i++)
//            innerApartments.add(Integer.parseInt(createAsset[i]));
        int innerApartments = Integer.parseInt(createAsset[5]);
        try{
            return new Asset(area, price, sold, street, boulevard, innerApartments);
        } catch (Exception e){

        }
         return null;
    }
//
//    /**
//     * This function gets an asset and adds it to the list of the existing assets.
//     * @param asset - the asset that will be added.
//     * @return - True if the operation succeeded. Otherwise, returns False.
//     */
//    public boolean addAssetToFile(Asset asset)
//    {
//        if (user.getRole() == Role.Agent)
//        {
//            if (asset == null)
//                throw new NullPointerException("Asset is null!");
//
//            try{
//                BufferedWriter writer = new BufferedWriter(new FileWriter("Files/Assets.txt"));
//                //maybe add a loop to the end of the file...
//                writer.write(asset.toString() + "\n"); // NEED TO CHECK HERE THE STRING
//                writer.close();
//                listOfAssets.add(asset);
//                return true;
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//
//            return false;
//        }
//
//        return false;
//    }

    /**
     * This function writes all the existing assets from the assets list to the file.
     */
    public void updateFile()
    {
        if (listOfAssets == null)
            throw new NullPointerException("List is empty!");

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("Files/Assets.txt"));

            for(int i = 0; i < listOfAssets.size(); i++)
            {
                writer.write(listOfAssets.get(i).toString() + "\n"); // NEED TO CHECK HERE THE STRING
                writer.close();
            }
            System.out.println("Update succeeded!");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Update failed!");
        }
    }

    /**
     * This function checks if the current user is authorized to perform such action.
     * @param role - the required Role to perform the action.
     * @throws UnAuthorizedUser - if the user is unauthorized to perform this action.
     */
    public void authorize(Role role)
    {
        if (user.getRole() != role)
            throw new UnAuthorizedUser();
    }

    // Authorized functions of Agent - update an asset

    /**
     * This function checks if the given line contains all the required inputs to create a new asset.
     * @param line - the given line.
     * @return true if the line is valid. Otherwise, returns false.
     */
    private boolean validLine(String line)
    {
        if (line == null)
            return false;

        // maybe add try
        String[] createAsset = line.split(",");
        double area = Double.parseDouble(createAsset[0]);
        int price = Integer.parseInt(createAsset[1]);
        boolean sold = Boolean.parseBoolean(createAsset[2]);
        int street = Integer.parseInt(createAsset[3]);
        int boulevard = Integer.parseInt(createAsset[4]);
        int innerApartments = Integer.parseInt(createAsset[5]);

        if (area < 0)
            throw new InvalidChoice("Area can't be negative!");
        if (price <= 0)
            throw new InvalidChoice("Price must be greater than 0!");
        if (innerApartments <= 0)
            throw new InvalidChoice("Inner apartments must be grater than 0!");

        return true;
    }

    private String enterAssetData()
    {
        System.out.println("Please enter the details of the asset as AREA,PRICE,SOLD,STREET,BOULEVARD,INNER APARTMENTS");
        System.out.println("Without inner apartments: 60,660000,true,2,3");
        System.out.println("With inner apartments: 60,660000,true,2,3,5");
        return reader.nextLine();
    }

    public void updateAsset()
    {
        authorize(Role.Agent);
        if (listOfAssets == null)
            throw new NullPointerException("No assets available!");

        System.out.println("Select the number of asset that you want to update");
        showAssets();
        int i = reader.nextInt();
        Asset asset = listOfAssets.get(i-1);
        if (asset == null)
            throw new NullPointerException("Asset is null!");

        String line = enterAssetData();
        while (!validLine(line))
        {
            System.out.println("Update failed!");
            line = enterAssetData();
        }

        String[] stringAsset = line.split(",");
        double area = Double.parseDouble(stringAsset[0]);
        int price = Integer.parseInt(stringAsset[1]);
        boolean sold = Boolean.parseBoolean(stringAsset[2]);
        int street = Integer.parseInt(stringAsset[3]);
        int boulevard = Integer.parseInt(stringAsset[4]);
        int innerApartments = Integer.parseInt(stringAsset[5]);

        // update
        asset.setArea(area);
        asset.setPrice(price);
        asset.setSold(sold);
        asset.setAddress(new Address(street, boulevard));
        asset.setInnerApartments(innerApartments);
        updateFile();
        initBuildingsList();
    }

    //

    // Authorized functions of Seller - see assets, remove an asset, notify the agent

    /**
     * This function prints all the assets.
     */
    public void showAssets()
    {
        //authorize(Role.Buyer);
        if (listOfAssets.isEmpty())
            System.out.println("No assets to show.");

        else
        {
            System.out.println("Area | Price | Is sold? | Address");
            for (int i = 0; i < listOfAssets.size(); i++)
                System.out.println(i+1 + ". " + listOfAssets.get(i).toString());
        }
    }

    /**
     * This function removes an asset.
     */
    public void removeAsset()
    {
        if (listOfAssets.isEmpty())
        {
            System.out.println("There are currently no assets.");
            return;
        }

        showAssets();
        System.out.println("\nPlease enter the number of asset that you want to remove");
        int i = reader.nextInt();

        if (i > listOfAssets.size() || i < 1)
            throw new InvalidChoice("The given input is not in range!");

        Asset toRemove = listOfAssets.get(i-1);
        // remove from the building
        for (int j = 0; j < listOfBuildings.size(); j++)
        {
            Building currentBuilding = listOfBuildings.get(j);
            if(currentBuilding.isExist(toRemove))
            {
                currentBuilding.removeAsset(toRemove);
                // if the building has no apartments in it -> remove it
                if (currentBuilding.getAssetsList().isEmpty())
                    listOfBuildings.remove(currentBuilding);

                break; // found the apartment -> stop the search
            }
        }

        // remove from list
        listOfAssets.remove(i-1);

        // remove from the file
        updateFile();
    }

    /**
     * this function adds an asset to the file.
     */
    public void addAsset()
    {
        Asset asset = null;
        while (true)
        {
            String line = reader.nextLine();
            asset = readAsset(line);
            if (asset != null)
                break;
            System.out.println("The input is not valid. Please try again.");
        }
        listOfAssets.add(asset);
        updateFile();
    }

    // Authorized functions of Buyer - see assets(already implemented), buy an asset




}
