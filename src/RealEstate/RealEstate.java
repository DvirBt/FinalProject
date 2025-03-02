package RealEstate;

import RealEstate.Exceptions.*;
import RealEstate.Notification.Notification;
import RealEstate.Entities.*;
import RealEstate.Strategy.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RealEstate {

    private static final RealEstate realEstate = new RealEstate();   // Create a Singleton class for system
    //private Role user;
    private Person user;
    private ArrayList<Asset> listOfAssets;
    Scanner reader = new Scanner(System.in);
    private ArrayList<Building> listOfBuildings;
    private ArrayList<Agent> agents;
    private ArrayList<Buyer> buyers;
    private ArrayList<Seller> sellers;
    private Notification notification;

    // Singleton
    private RealEstate()
    {
        agents = new ArrayList<>();
        buyers = new ArrayList<>();
        sellers = new ArrayList<>();
        initUsers();
        listOfAssets = new ArrayList<>();
        initAssetsList();
        listOfBuildings = new ArrayList<>();
        initBuildingsList();
        notification = new Notification();
        initObservers();
    }

    public void setUser(Person person)
    {
        user = person;
    }

    public static RealEstate getInstance()
    {
        return realEstate;
    }

    /**
     * This function checks if a user exists in the system by entering inputs in run time.
     * This function will continue to run until the user will enter matching first name, last name and role.
     * @throws UnRegisteredUser if no user is found in the system with the given inputs.
     */
    public void login() throws UnRegisteredUser
    {
        boolean found = false;
        while (!found)
        {
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
                    System.out.println("Invalid input");
            }

            try {
                if (!found)
                    throw new UnRegisteredUser();
            } catch (UnRegisteredUser e)
            {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nWelcome back " + user.getFirstName() + " " + user.getLastName());
    }

    /**
     * This function gets a maximum number and requires from the user to enter a number from 0 to the max number.
     * @param num the maximum number that the user can input.
     * @return the number the user entered.
     * @throws InvalidChoice if the input is not in range.
     */
    private int makeChoice(int num)
    {
        while (true)
        {
            reader = new Scanner(System.in);
            if (reader.hasNextInt())
            {
                int input = reader.nextInt();
                reader.nextLine(); // remove the "Enter" as a given input
                try {
                    if (input <= num && input >= 0)
                        return input;

                    throw new InvalidChoice("The given input is invalid!\nPlease enter a number between 0-" + num);
                } catch (InvalidChoice e) {
                    System.out.println(e.getMessage());
                }
            }
            else
                System.out.println("Wrong format of input!\nEnter a number!");

        }
    }

    /**
     * This function initialize notification data member.
     */
    private void initObservers()
    {
        for (Agent agent : agents)
            notification.register(agent);

        for (Buyer buyer : buyers)
            notification.register(buyer);

        for (Seller seller : sellers)
            notification.register(seller);
    }

    /**
     * This function initialize all the existing users from the file "Users".
     */
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

        String[] createAsset = line.split(",");
        String firstName = createAsset[0];
        String lastName = createAsset[1];
        int role = Integer.parseInt(createAsset[2]);
        UserFactory userFactory = new UserFactory();
        return userFactory.createUserType(firstName, lastName, role);
    }

    /**
     * This function reads all assets from the file "Assets" and initializes the assets list.
     */
    private void initAssetsList()
    {
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
     * This function initialize the buildings list.
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
     * This functions gets a string as an asset and transfers it to an asset object.
     * @param line the given input.
     * @return the asset if the input is valid. Otherwise, returns null.
     */
    private Asset readAsset(String line)
    {
        if (line == null)
            return null;

        String[] createAsset = line.split(",");
        int agentID = Integer.parseInt(createAsset[0]);
        int sellerID =  Integer.parseInt(createAsset[1]);
        Agent agent = (Agent) selectPersonByID(agentID);
        Person owner = selectPersonByID(sellerID);
        int area = Integer.parseInt(createAsset[2]);
        int price = Integer.parseInt(createAsset[3]);
        boolean sold = Boolean.parseBoolean(createAsset[4]);
        int boulevard = Integer.parseInt(createAsset[5]);
        int street = Integer.parseInt(createAsset[6]);
        int innerApartments = Integer.parseInt(createAsset[7]);

        return new Asset(area, price, sold, street, boulevard, innerApartments, agent, owner);
    }

    /**
     * This function updates the file "Assets" by writing all the existing assets from the assets list to the file.
     */
    public void updateFile()
    {
        if (listOfAssets.isEmpty())
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
    public boolean authorize(Role role) throws UnAuthorizedUser
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
     * This function selects the person by a given id.
     * @param id the given id of the person.
     * @return the person with the matching ID. If one does not exist - returns null.
     */
    private Person selectPersonByID(int id)
    {
        // get all the users to one list
        ArrayList<Person> allUsers = new ArrayList<>();
        allUsers.addAll(agents);
        allUsers.addAll(buyers);
        allUsers.addAll(sellers);

        for (int i = 0; i < allUsers.size(); i++)
        {
            if (allUsers.get(i).getId() == id)
                return allUsers.get(i);
        }

        return null;
    }

    /**
     * This function checks if the given line contains all the required inputs to create a new asset.
     * @param line the given line.
     * @return true if the line is valid. Otherwise, returns false.
     * @throws InvalidData when not enough or more than needed inputs entered
     */
    private boolean validLine(String line)
    {
        if (line == null)
            return false;
        String[] createAsset = line.split(",");
        try {
            if (createAsset.length < 7 || createAsset.length > 8)
                throw new InvalidData();
        } catch (InvalidData e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            int agentID = Integer.parseInt(createAsset[0]);
            int sellerID =  Integer.parseInt(createAsset[1]);
            Agent agent = (Agent) selectPersonByID(agentID);
            Person owner = selectPersonByID(sellerID);
            int area = Integer.parseInt(createAsset[2]);
            int price = Integer.parseInt(createAsset[3]);
            boolean sold = Boolean.parseBoolean(createAsset[4]);
            int boulevard = Integer.parseInt(createAsset[5]);
            int street = Integer.parseInt(createAsset[6]);
            int innerApartments = 0;
            if (createAsset.length == 8)
                innerApartments = Integer.parseInt(createAsset[7]);

            if (agent == null || agent.getRole() != Role.Agent)
                throw new InvalidChoice("Not an agent!");
            if (owner == null)
                throw new InvalidChoice("Not a person!");
            if (area <= 0)
                throw new InvalidChoice("Area must be positive!");
            if (price <= 0)
                throw new InvalidChoice("Price must be greater than 0!");
            if (innerApartments < 0)
                throw new InvalidChoice("Inner apartments can't be negative!");

        } catch (NumberFormatException e) // for wrong input
        {
            System.out.println(e.getMessage());
            return false;
        }
        catch (InvalidChoice e) // for invalid choice
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * This function prints all the agents.
     */
    private void showAgents()
    {
        System.out.println("Agents:");
        for (int i = 0; i < agents.size(); i++)
            System.out.println((i+1) + ". " + agents.get(i).toString());
    }

    /**
     * This function prints all the sellers.
     */
    private void showSellers()
    {
        System.out.println("Sellers:");
        for (int i = 0; i < sellers.size(); i++)
            System.out.println((i+1) + ". " + sellers.get(i).toString());
    }

    /**
     * This function gets an asset and requires the user to input new data to this asset.
     * @param asset the asset that will be updated.
     * @return a string of the new data properties of the asset.
     */
    private String enterAssetData(Asset asset)
    {
        System.out.println("Please enter the details of the asset as AREA,PRICE,SOLD,BOULEVARD,STREET,INNER APARTMENTS");
        System.out.println("For Example:");
        System.out.println("Without inner apartments: 60,660000,true,2,3 or 60,660000,true,2,3,0");
        System.out.println("With inner apartments: 60,660000,true,2,3,5");
        System.out.println("Current asset details: " + asset.getValues().substring(4)); // remove the part of agent's and seller's ID
        //reader = new Scanner(System.in);
        String s = reader.nextLine();
        // show all agents
        showAgents();;
        System.out.println("\nEnter the number of the new agent");
        int choice = makeChoice(agents.size());
        //reader.nextLine();
        if (choice == 0)
        {
            System.out.println("Update canceled!");
            return "";
        }
        Agent agent = agents.get(choice-1);

        // show all sellers
        showSellers();
        System.out.println("\nEnter the number of the new owner");
        choice = makeChoice(sellers.size());
        if (choice == 0)
        {
            System.out.println("Update canceled!");
            return "";
        }
        Seller seller = sellers.get(choice-1);

        s = agent.getId()+ "," + seller.getId() + "," + s;
        return s;
    }

    /**
     * This function updates an asset and updates the file.
     * @return true if the asset updated successfully. Otherwise, returns false.
     * @throws InvalidAddress if the asset is trying to be added on an undivided asset or change a private asset to public and the opposite.
     * @throws UnAuthorizedUser if the user is not authorized to perform this action.
     */
    public boolean updateAsset() throws UnAuthorizedUser
    {
        if (!authorize(Role.Agent))
            return false;

        ArrayList<Asset> assets = showApartments();
        if (assets == null)
            return false;

        System.out.println("\nSelect the number of asset that you want to update. To cancel enter 0");
        int i = makeChoice(assets.size());

        if (i == 0)
        {
            System.out.println("Update canceled!");
            return false;
        }

        Asset asset = assets.get(i-1);
        String line = enterAssetData(asset);
        if (!validLine(line))
            return false;

        String[] stringAsset = line.split(",");
        int agentID = Integer.parseInt(stringAsset[0]);
        int sellerID =  Integer.parseInt(stringAsset[1]);
        Agent agent = (Agent) selectPersonByID(agentID);
        Person owner = selectPersonByID(sellerID);
        int area = Integer.parseInt(stringAsset[2]);
        int price = Integer.parseInt(stringAsset[3]);
        boolean sold = Boolean.parseBoolean(stringAsset[4]);
        int boulevard = Integer.parseInt(stringAsset[5]);
        int street = Integer.parseInt(stringAsset[6]);
        int innerApartments = 0;
        if (stringAsset.length == 8)
            innerApartments = Integer.parseInt(stringAsset[7]);

        // check if the given address is occupied by an asset that is not divided
        try
        {
            for (int j = 0; j < listOfBuildings.size(); j++)
            {
                Building currentBuilding = listOfBuildings.get(j);
                if (currentBuilding.getAssetsList().contains(asset))
                {
                    // if the building is private and trying to be updated to public building -> can't be done
                    // removed it because a private house can become public I guess..
                    // if (!currentBuilding.isDivided() && innerApartments != 0)
                    //      throw new InvalidAddress("This asset is private (not divided) and can not become a public one (divided)!\nMeaning, it has no inner apartments in it!");

                    // a public building (divided) can not become private one
                    if (currentBuilding.isDivided() && innerApartments == 0)
                        throw new InvalidAddress("This asset is public (divided) and can not become a private one (not divided)!\nMeaning, it has inner apartments in it!");

                    break;
                }
            }

            for (int j = 0; j < listOfBuildings.size(); j++)
            {
                if (listOfBuildings.get(j).getAddress().getStreet() == street && listOfBuildings.get(j).getAddress().getBoulevard() == boulevard)
                {
                    // if the building is private without the current asset in it -> can't be updated because a private asset exists in this address!
                    if (!listOfBuildings.get(j).isDivided() && !listOfBuildings.get(j).getAssetsList().contains(asset))
                        throw new InvalidAddress("A private asset (not divided) already exists at this address!\nMeaning, a private house exists in this address!");

                    // if the building is divided and an asset in it tries to become private while other apartments in it -> can't be done
                    else if (listOfBuildings.get(j).isDivided() && innerApartments == 0)
                        throw new InvalidAddress("A private asset (not divided) can not be added to a public one (divided)!\nMeaning, a private house can not be added to a building!");

                    break;
                }
            }
        } catch (InvalidAddress e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        // update
        asset.setArea(area);
        asset.setPrice(price);
        asset.setSold(sold);
        asset.setAddress(street, boulevard);
        asset.setInnerApartments(innerApartments);
        asset.setAgentOfAsset(agent);
        asset.setOwner(owner);
        updateFile();
        listOfBuildings.clear(); // need update the buildings
        initBuildingsList(); // build them again due to possible changes
        return true;
    }

    // END of authorized action of Agent

    // Authorized functions of Seller - see assets, remove an asset, notify an agent

    /**
     * This function checks if the current user has assets belongs to him.
     * @return a list of the assets that belongs to this user. If the user has no assets that belongs to him the function returns null.
     */
    private ArrayList<Asset> showApartments()
    {
        ArrayList<Asset> assets = new ArrayList<>();

        if (user instanceof Agent)
        {
            for (int i = 0 ; i < listOfAssets.size(); i++)
            {
                if (listOfAssets.get(i).getAgentOfAsset().getId() == user.getId()) // if this asset belongs to this agent (user)
                    assets.add(listOfAssets.get(i));
            }
        }

        else // user instance of Seller
        {
            for (int i = 0 ; i < listOfAssets.size(); i++)
            {
                if (listOfAssets.get(i).getOwner().getId() == user.getId()) // if this asset belongs to this seller (user)
                    assets.add(listOfAssets.get(i));
            }
        }

        if (assets.isEmpty())
        {
            System.out.println("This user has no assets!");
            return null;
        }

        else
        {
            System.out.println("The assets belongs to you:");
            for (int i = 0; i < assets.size(); i++)
                System.out.println(i+1 + ". " + assets.get(i).toString());
        }

        return assets;
    }

    /**
     * This function prints all the assets.
     * @return true if there are assets to show. Otherwise, returns false.
     */
    public boolean showAssets()
    {
        // if i use !authorize(Role.Seller) && !authorize(Role.Buyer) -> two UnAuthorizedUser exception appear!
        try{
            if (user.getRole() == Role.Agent)
                throw new UnAuthorizedUser();
        } catch (UnAuthorizedUser e) {
            System.out.println(e.getMessage());
            return false;
        }

        if (listOfBuildings.isEmpty())
        {
            System.out.println("No assets to show.");
            return false;
        }

        else
        {
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
     * @throws UnAuthorizedUser if the user is not authorized to perform this action.
     */
    public boolean removeAsset() throws UnAuthorizedUser
    {
        if (!authorize(Role.Seller))
            return false;

        ArrayList<Asset> assets = showApartments();
        if (assets == null)
            return false;

        System.out.println("\nPlease enter the number of asset that you want to remove. To cancel enter 0");
        int i = makeChoice(assets.size());

        if (i == 0)
        {
            System.out.println("Remove canceled!");
            return false;
        }

        Asset toRemove = assets.get(i-1);
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
        listOfAssets.remove(toRemove);

        // remove from the file -> update the file
        updateFile();

        System.out.println("Would you like to notify the agent of this asset? Enter yes if you want to");
        String answer = reader.nextLine();
        if (answer.equals("yes") || answer.equals("Yes"))
        {
            System.out.println("Enter your message?");
            answer = reader.nextLine();
            notification.notifyAgent(toRemove.getAgentOfAsset(), answer);
        }

        return true;
    }

    /**
     * This function notifies an agent(s).
     * @return if the agent received the message. Otherwise, returns false.
     * @throws UnAuthorizedUser if the user is not authorized to perform this action.
     */
    public boolean notifyService() throws UnAuthorizedUser
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
     * @throws UnAuthorizedUser if the user is not authorized to perform this action.
     */
    public boolean closeDeal() throws UnAuthorizedUser
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

        System.out.println("\nEnter the agent of your new asset. To cancel enter 0");
        for (int i = 0; i < agents.size(); i++)
            System.out.println(i+1 + ". " + agents.get(i).toString());

        choice = makeChoice(agents.size());
        Agent agentOfAsset = agents.get(choice-1);

        System.out.println("\nWould you like to add additional services? If so enter yes");
        //reader = new Scanner(System.in);
        String service = reader.nextLine();
        ArrayList<AdditionalService> services = new ArrayList<>();
        if (service.equals("yes") || service.equals("Yes"))
        {
            boolean exit = false;
            while (!exit && services.size() != 4)
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

        buyAsset.setSold(true);
        buyAsset.setOwner(user);
        buyAsset.setAgentOfAsset(agentOfAsset);

        Deal deal = new Deal(buyAsset, services);

        System.out.println("The deal:\n" + deal.toString());
        updateFile();
        return true;
    }

    // END of authorized actions of Seller

    // Authorized action by all users - Pull assets

    /**
     * This function prints all the relevant assets based on the inputs: asset, radius and a given method.
     * @return true if assets displayed successfully. Otherwise, returns false.
     */
    public boolean pullAssets()
    {
        if (listOfAssets.isEmpty())
            return false;

        for (int i = 0; i < listOfAssets.size(); i++)
            System.out.println((i+1) + ". " + listOfAssets.get(i).toString());

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
        int radius = makeChoice(Integer.MAX_VALUE);
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

            if (!stop && search != null)
                search.executeStrategy();
        }

        return true;
    }
}