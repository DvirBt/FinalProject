package RealEstate;

import RealEstate.Exceptions.UnAuthorizedUser;
import RealEstate.Exceptions.UnRegisteredUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnRegisteredUser, UnAuthorizedUser {
        System.out.println("Hello!");
        // create a Scanner to read the inputs
        Scanner reader = new Scanner(System.in);
        RealEstate realEstate = RealEstate.getInstance();
        realEstate.login();
        boolean stop = false;
        while (!stop)
        {
            System.out.println("\nAvailable actions:\n1. See assets\n2. Update assets\n3. Remove asset\n4. Notify agent\n5. Pull assets\n6. Close a deal\n0. Exit");
            int choice = reader.nextInt();
            boolean operation;
            switch (choice)
            {
                case 1:
                    operation = realEstate.showAssets();
                    if (operation)
                        System.out.println("Assets displayed successfully!");
                    else
                        System.out.println("Assets displayed failed!");
                    break;
                case 2:
                    operation = realEstate.updateAsset();
                    if (operation)
                        System.out.println("Asset updated successfully!");
                    else
                        System.out.println("Asset updated failed!");
                    break;
                case 3:
                    operation = realEstate.removeAsset();
                    if (operation)
                        System.out.println("Asset removed successfully!");
                    else
                        System.out.println("Asset removed failed!");
                    break;
                case 4:
                    operation = realEstate.notifyService();
                    if (operation)
                        System.out.println("Notify successfully!");
                    else
                        System.out.println("Notify failed!");
                    break;
                case 5:
                    operation = realEstate.pullAssets();
                    if (operation)
                        System.out.println("Assets pulled successfully!");
                    else
                        System.out.println("Asset pulled failed!");
                    break;
                case 6:
                    operation = realEstate.closeDeal();
                    if (operation)
                        System.out.println("Deal closed successfully!");
                    else
                        System.out.println("Deal closed failed!");
                    break;

                case 0:
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input, try again!\n");
            }
        }

        System.out.println("Exit successfully!");
    }
}
