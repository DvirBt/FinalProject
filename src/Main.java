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

            switch (choice)
            {
                // Agent
                case 1:
                    System.out.println("What action would you like to perform\n1.");
                // Buyer
                case 2:
                // Seller
                case 3:
                default:
                    stop = true;
            }
        }

        System.out.println("Exit successfully!");
    }

    private static void loginSystem()
    {
        System.out.println("Please enter as what you want to login to the system:\n1. Agent\n2. Buyer\n 3. Seller\n4. Exit");
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

                throw new InvalidChoice();
            }
            catch (InvalidChoice e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}