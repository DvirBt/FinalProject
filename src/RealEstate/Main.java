package RealEstate;

import RealEstate.Entities.Person;
import RealEstate.Entities.UserFactory;
import RealEstate.Exceptions.InvalidChoice;
import RealEstate.RealEstate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        // create a Scanner to read the inputs
        Scanner reader = new Scanner(System.in);
        loginSystem();
        boolean stop = false;
        while (!stop)
        {
            int choice = makeChoice();
            System.out.println("Enter your name");
            String name = reader.nextLine();
            UserFactory userFactory = new UserFactory();
            Person p = userFactory.createUserType(name, choice);
            RealEstate.setInstance(p);
        }

        System.out.println("Exit successfully!");
    }

    private static void loginSystem()
    {
        System.out.println("Login as:\n1. Agent\n2. Buyer\n 3. Seller");
    }

    private static void showActions()
    {
        System.out.println("The available actions:\n1.");
    }

    private static int makeChoice()
    {
        // create a Scanner to read the inputs
        Scanner reader = new Scanner(System.in);
        loginSystem();
        int input = reader.nextInt();
        while (input != 9)
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

    public removeAnAsset()
    {

    }
}
