import Entities.Person;
import Entities.UserFactory;
import Exceptions.InvalidChoice;

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
            UserFactory userFactory = new UserFactory();
            Person p = userFactory.createUserType(choice);
            RealEstate.setInstance(p);
        }

        System.out.println("Exit successfully!");
    }

    private static void loginSystem()
    {
        System.out.println("Please enter as what you want to login to the system:\n1. Agent\n2. Buyer\n 3. Seller");
    }

    private static int makeChoice()
    {
        // create a Scanner to read the inputs
        Scanner reader = new Scanner(System.in);
        loginSystem();
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

    public removeAnAsset()
    {

    }
}
