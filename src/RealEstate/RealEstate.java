package RealEstate;

import Notification.Notification;
import Notification.Observer;
import RealEstate.Entities.*;
import RealEstate.Exceptions.InvalidChoice;
import RealEstate.Exceptions.UnAuthorizedUser;
import RealEstate.Strategy.*;

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
    private ArrayList<Observer> observers;
    private Notification notification;

    // Singleton
    private RealEstate()
    {
        listOfAssets = new ArrayList<>();
        initAssetsList();
        listOfBuildings = new ArrayList<>();
        initBuildingsList();
        agents = new ArrayList<>();
        buyers = new ArrayList<>();
        sellers = new ArrayList<>();
        initUsers();
        observers = new ArrayList<>();
        notification = new Notification();
        initObservers();
    }

    public static RealEstate getInstance()
    {
        return realEstate;
    }

    public ArrayList<Asset> getListOfAssets()
    {
        return listOfAssets;
    }

    /**
     * This function checks if a user exists in the system by entering inputs in run time.
     * This function will continue to run until the user will enter matching first name, last name and role.
     */
    public void login()
    {
        boolean found = false;
        while (!found)
        {
            //reader = new Scanner(System.in);
            System.out.println("Enter first name");
            String firstName = reader.nextLine();
            System.out.println("Enter last name");
            String lastName = reader.nextLine();
            System.out.println("Login as:\n1. Agent\n2. Buyer\n3. Seller");
            int choice = makeChoice(3);
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
            }

            if (!found)
                System.out.println("Unregistered user, try again!");
        }

        System.out.println("\nWelcome back " + user.getFirstName() + " " + user.getLastName());
    }

    /**
     * This function gets a maximum number and requires from the user to enter a number in the range 0 - max number.
     * @param num the maximum number that the user can input.
     * @return the number the user entered.
     * @throws InvalidChoice if the input is not in range.
     */
    private int makeChoice(int num)
    {
        while (true)
        {
            //reader = new Scanner(System.in);
            int input = reader.nextInt();
            reader.nextLine(); // remove the enter as a given input
            try
            {
                if (input <= num && input >= 0)
                    return input;

                throw new InvalidChoice("The given input is invalid!\nPlease enter a number between 0-" + num);
            }
            catch (InvalidChoice e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private void initObservers()
    {
//        observers.addAll(agents);
//        observers.addAll(sellers);
//        observers.addAll(buyers);
        for (Agent agent : agents)
            notification.register(agent);

        for (Buyer buyer : buyers)
            notification.register(buyer);

        for (Seller seller : sellers)
            notification.register(seller);
    }

    private void initUsers()
    {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/RealEstate/Files/Users"));
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
     * This function reads all assets from the file and initializes the assets list.
     */
    private void initAssetsList()
    {
//        File file = new File("src/RealEstate/Files/Assets.txt");
//        if (file.exists()) {
//            System.out.println("File exists!");
//        } else {
//            System.out.println("File NOT found! Check the path.");
//        }
        try{
            BufferedReader read = new BufferedReader(new FileReader("src/RealEstate/Files/Assets.txt"));
            String line = read.readLine();
            while (line != null)
            {
                Asset asset = readAsset(line);
                if (asset != null)
                    listOfAssets.add(asset);
                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function initialize the buildings.
     */
    private void initBuildingsList()
    {
        boolean exist;
        for (int i = 0; i < listOfAssets.size(); i++)
        {
            Asset currentAsset = listOfAssets.get(i);
            exist = false;
            for (int j = 0; j < listOfBuildings.size(); j++)
            {
                Building currentBuilding = listOfBuildings.get(j);
                if (currentAsset.getAddress().getBoulevard() == currentBuilding.getAddress().getBoulevard() && currentAsset.getAddress().getStreet() == currentBuilding.getAddress().getStreet())
                {
                    // check if the building is private or not
                    if (!currentBuilding.isDivided())
                        return;

                    exist = true;
                    currentBuilding.add(currentAsset);
                    break;
                }
            }

            if (!exist)
            {
                Building building = new Building(currentAsset);
                listOfBuildings.add(building);
            }
        }
    }

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
        int area = Integer.parseInt(createAsset[0]);
        int price = Integer.parseInt(createAsset[1]);
        boolean sold = Boolean.parseBoolean(createAsset[2]);
        int boulevard = Integer.parseInt(createAsset[3]);
        int street = Integer.parseInt(createAsset[4]);
        //ArrayList<Integer> innerApartments = new ArrayList<>();
//        for (int i = 5; i < createAsset.length; i++)
//            innerApartments.add(Integer.parseInt(createAsset[i]));
        int innerApartments = 0;
        if (createAsset.length == 6)
            innerApartments = Integer.parseInt(createAsset[5]);
        try{
            return new Asset(area, price, sold, street, boulevard, innerApartments);
        } catch (Exception e){

        }
         return null;
    }

    /**
     * This function writes all the existing assets from the assets list to the file.
     */
    public void updateFile()
    {
        if (listOfAssets == null)
            throw new NullPointerException("List is empty!");

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/RealEstate/Files/Assets.txt"));

            for(int i = 0; i < listOfAssets.size(); i++)
            {
                String s = listOfAssets.get(i).getValues();
                writer.write(s+ "\n");
            }
            writer.close();
            System.out.println("Update succeeded!");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Update failed!");
        }
    }

    /**
     * This function checks if the current user is authorized to perform such action.
     * @param role the required Role to perform the action.
     * @return true if the user is authorized. Otherwise, returns false.
     * @throws UnAuthorizedUser if the user is unauthorized to perform this action.
     */
    public boolean authorize(Role role)
    {
        try{
            if (user.getRole() != role)
                throw new UnAuthorizedUser();
        } catch (UnAuthorizedUser e)
        {
            System.out.println(e.getMessage());
            //e.printStackTrace();
            return false;
        }

        return true;
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

        String[] createAsset = line.split(",");
        if (createAsset.length < 5 || createAsset.length > 6)
        {
            System.out.println("Not enough inputs entered!"); // throw
            return false;
        }
        try {
            int area = Integer.parseInt(createAsset[0]);
            int price = Integer.parseInt(createAsset[1]);
            boolean sold = Boolean.parseBoolean(createAsset[2]);
            int boulevard = Integer.parseInt(createAsset[3]);
            int street = Integer.parseInt(createAsset[4]);
            int innerApartments = 0;
            if (createAsset.length == 6)
                innerApartments = Integer.parseInt(createAsset[5]);

            if (area < 0)
                throw new InvalidChoice("Area can't be negative!");
            if (price <= 0)
                throw new InvalidChoice("Price must be greater than 0!");
            if (innerApartments < 0)
                throw new InvalidChoice("Inner apartments can't be negative!");

        } catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        catch (InvalidChoice e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    private String enterAssetData(Asset asset)
    {
        System.out.println("Please enter the details of the asset as AREA,PRICE,SOLD,BOULEVARD,STREET,INNER APARTMENTS");
        System.out.println("For Example:");
        System.out.println("Without inner apartments: 60,660000,true,2,3");
        System.out.println("With inner apartments: 60,660000,true,2,3,5");
        System.out.println("Current asset details: " + asset.getValues());
        //reader = new Scanner(System.in);
        String s = reader.nextLine();
        return s;
    }

    /**
     * This function updates an asset.
     * @return true if the asset updated successfully. Otherwise, returns false.
     */
    public boolean updateAsset()
    {
        if (!authorize(Role.Agent))
            return false;

        if (!showApartments())
            return false;

        System.out.println("\nSelect the number of asset that you want to update. To cancel enter 0");
        int i = makeChoice(listOfAssets.size());

        if (i == 0)
        {
            System.out.println("Update canceled!");
            return false;
        }

        Asset asset = listOfAssets.get(i-1);
        String line = enterAssetData(asset);
        if (!validLine(line))
            return false;

//        while (!validLine(line))
//        {
//            System.out.println("Update failed!");
//            line = enterAssetData();
//        }

        String[] stringAsset = line.split(",");
        int area = Integer.parseInt(stringAsset[0]);
        int price = Integer.parseInt(stringAsset[1]);
        boolean sold = Boolean.parseBoolean(stringAsset[2]);
        int boulevard = Integer.parseInt(stringAsset[3]);
        int street = Integer.parseInt(stringAsset[4]);
        int innerApartments = 0;
        if (stringAsset.length == 6)
            innerApartments = Integer.parseInt(stringAsset[5]);

        // update
        asset.setArea(area);
        asset.setPrice(price);
        asset.setSold(sold);
        asset.setAddress(new Address(street, boulevard));
        asset.setInnerApartments(innerApartments);
        updateFile();
        initBuildingsList();
        return true;
    }

    // END of authorized action of Agent

    // Authorized functions of Seller - see assets, remove an asset, notify an agent

    /**
     * This function prints all the apartments.
     * @return true if there are apartments to show. Otherwise, returns false.
     */
    private boolean showApartments()
    {
        if (listOfAssets.isEmpty())
        {
            System.out.println("No assets to show!");
            return false;
        }

        else
        {
            //System.out.println("Area | Price | Is sold? | Address");
            for (int i = 0; i < listOfAssets.size(); i++)
                System.out.println(i+1 + ". " + listOfAssets.get(i).toString());
        }

        return true;
    }

    /**
     * This function prints all the assets.
     * @return true if the assets are shown. Otherwise, returns false.
     */
    public boolean showAssets()
    {
        if (listOfBuildings.isEmpty())
        {
            System.out.println("No assets to show.");
            return false;
        }

        else
        {
            //System.out.println("Area | Price | Is sold? | Address");
            for (int i = 0; i < listOfBuildings.size(); i++)
            {
                System.out.println("Building number: " + (i+1));
                System.out.println(listOfBuildings.get(i).toString());
            }
        }

        return true;
    }

    /**
     * This function removes an asset.
     * @return true if the asset removed successfully. Otherwise, returns false.
     */
    public boolean removeAsset()
    {
        if (!showApartments())
            return false;

        System.out.println("\nPlease enter the number of asset that you want to remove. To cancel enter 0");
        int i = makeChoice(listOfAssets.size());

        if (i == 0)
        {
            System.out.println("Remove canceled!");
            return false;
        }

        Asset toRemove = listOfAssets.get(i-1);
        // remove from the building
        for (int j = 0; j < listOfBuildings.size(); j++)
        {
            Building currentBuilding = listOfBuildings.get(j);
            if(currentBuilding.isExist(toRemove))
            {
                currentBuilding.remove(toRemove);
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
        return true;
    }

    public boolean notifyService()
    {
        if (!authorize(Role.Seller))
            return false;

        if (agents.isEmpty())
        {
            System.out.println("There are currently no agents available!");
            return false;
        }

        System.out.println("\nChoose an option:\n1. Notify specific agent\n2. Notify everyone\n0. Exit");
        int choice = makeChoice(2);
        if (choice == 0)
        {
            System.out.println("Notify canceled!");
            return false;
        }
        String message;
        switch (choice)
        {
            case 1:
                for (int i = 0; i < agents.size(); i++)
                    System.out.println((i+1) + ". " + agents.get(i).toString());
                System.out.println("Enter the number of the agent that you want to sent a message. To cancel enter 0\n");
                choice = makeChoice(agents.size());
                if (choice == 0)
                {
                    System.out.println("Notify canceled!");
                    return false;
                }
                Agent agent = agents.get(choice-1);
                System.out.println("Please enter the message that you want to send");
                //reader = new Scanner(System.in);
                message = reader.nextLine();
                notification.notifyAgent(agent, message);
                break;

            case 2:
                System.out.println("Please enter the message that you want to send to everyone");
                //reader = new Scanner(System.in);
                message = reader.nextLine();
                notification.notifyAllObservers(message);
                break;

            default:
                System.out.println("No message sent!");
                return false;
        }

        return true;
    }

    // END of authorized functions of Seller

    // Authorized functions of Buyer - buy an asses

    /**
     * This function closes a deal between a buyer and an agent.
     * @return true if a deal has been made. Otherwise, returns false.
     */
    public boolean closeDeal()
    {
        if (!authorize(Role.Buyer))
            return false;

        // show available assets to buy
        ArrayList<Asset> buyAssets = new ArrayList<>();

        for (int i = 0; i < listOfAssets.size(); i++)
        {
            if (!listOfAssets.get(i).isSold())
                buyAssets.add(listOfAssets.get(i));
        }

        if (buyAssets.isEmpty())
        {
            System.out.println("No assets available for purchase!");
            return false;
        }

        System.out.println("\nAvailable assets: ");
        for (int i = 0; i < buyAssets.size(); i++)
            System.out.println(i+1 + ". " + buyAssets.get(i).toString());

        System.out.println("\nPlease select the number of the asset that you want to buy. To cancel enter 0");
        int choice = makeChoice(buyAssets.size());

        if (choice == 0)
        {
            System.out.println("Deal canceled!");
            return false;
        }

        Asset buyAsset = buyAssets.get(choice-1);

        if (agents.isEmpty())
        {
            System.out.println("Sorry but there are currently no agents available!");
            return false;
        }

        System.out.println("\nEnter the agent that helped you. To cancel enter 0");
        for (int i = 0; i < agents.size(); i++)
            System.out.println(i+1 + ". " + agents.get(i).toString());

        choice = makeChoice(agents.size());
        Agent agent = agents.get(choice-1);

        System.out.println("\nWould you like to add additional services? If so enter yes");
        //reader = new Scanner(System.in);
        String service = reader.nextLine();
        ArrayList<AdditionalService> services = new ArrayList<>();
        if (service.equals("yes") || service.equals("Yes"))
        {
            boolean exit = false;
            while (!exit)
            {
                System.out.println("Enter which service would you like to add\n1. Guarantee\n2. Clean\n3. Transfer\n4. Decorate\n0. Exit");
                choice = makeChoice(4);
                switch (choice)
                {
                    case 1:
                        if (!services.contains(AdditionalService.Guarantee))
                            services.add(AdditionalService.Guarantee);
                        else
                            System.out.println("This service is already included!");
                        break;
                    case 2:
                        if (!services.contains(AdditionalService.Clean))
                            services.add(AdditionalService.Clean);
                        else
                            System.out.println("This service is already included!");
                        break;
                    case 3:
                        if (!services.contains(AdditionalService.Transfer))
                            services.add(AdditionalService.Transfer);
                        else
                            System.out.println("This service is already included!");
                        break;
                    case 4:
                        if (!services.contains(AdditionalService.Decorate))
                            services.add(AdditionalService.Decorate);
                        else
                            System.out.println("This service is already included!");
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input, try again!");
                }
            }
        }

        Deal deal = new Deal(buyAsset, (Buyer)user, agent, services);
        buyAsset.setSold(true);
        System.out.println("The deal:\n" + deal.toString());
        updateFile();
        return true;
    }

    // END of authorized actions of Seller

    // Authorized action by all users - Pull assets

    /**
     * This function prints all the relevant assets based on the inputs: asset, radius and a given operation.
     * @return true if assets displayed successfully. Otherwise, returns false.
     */
    public boolean pullAssets()
    {
        if (!showApartments())
            return false;

        System.out.println("\nSelect the number of asset that you want as a center. To cancel enter 0");
        int choice = makeChoice(listOfAssets.size());

        if (choice == 0)
        {
            System.out.println("Pull canceled!");
            return false;
        }

        //reader = new Scanner(System.in);
        Asset asset = listOfAssets.get(choice-1);
        System.out.println("Enter a radius as an integer");
        int radius = reader.nextInt();
        reader.nextLine();
        try
        {
            if (radius < 0)
                throw new InvalidChoice("Radius can not be negative!");
        } catch (InvalidChoice e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        boolean stop = false;
        Search search = null;
        while (!stop)
        {
            System.out.println("\nShow nearby assets by:\n1. Average price\n2. Sold assets\n3. Up for sale assets\n4. Above current asset price\n5. Below current asset price\n6. Equal to current asset price\n0. Exit");
            System.out.println("Select the number of the method that you would like to perform");
            choice = makeChoice(6);
            switch (choice)
            {
                case 1:
                    search = new Search(new AveragePrice(listOfAssets, asset, radius));
                    break;
                case 2:
                    search = new Search(new SoldAssets(listOfAssets, asset, radius));
                    break;
                case 3:
                    search = new Search(new UpForSale(listOfAssets, asset, radius));
                    break;
                case 4:
                    search = new Search(new AbovePrice(listOfAssets, asset, radius));
                    break;
                case 5:
                    search = new Search(new BelowPrice(listOfAssets, asset, radius));
                    break;
                case 6:
                    search = new Search(new EqualPrice(listOfAssets, asset, radius));
                    break;
                case 0:
                    System.out.println("Exit successfully!");
                    stop = true;
                    break;
                default:
                    System.out.println("Error");
            }

            if (!stop)
                search.executeStrategy();
        }

        return true;
    }
}