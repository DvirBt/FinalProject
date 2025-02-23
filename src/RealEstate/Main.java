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
        RealEstate realEstate = RealEstate.getInstance();
        realEstate.login();
        boolean stop = false;
        while (!stop)
        {
            System.out.println("Available actions:\n1. See assets\n2. Edit assets\n3. Remove asset\n4. Notify agent\n5. Pull assets\n6. Close a deal\n9. Exist");
            int choice = reader.nextInt();
            switch (choice)
            {
                case 1:
                    realEstate.showAssets();
                    break;
                case 2:
                    realEstate.updateAsset();
                    break;
                case 3:
                    realEstate.removeAsset();
                    break;
                case 9:
                    stop = true;
                default:
                    System.out.println("Invalid input, try again!");
            }
        }

        System.out.println("Exit successfully!");
        while (true);
    }
}
