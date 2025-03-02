package RealEstate.Tests;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.Entities.Role;
import RealEstate.Entities.Seller;
import RealEstate.Exceptions.UnAuthorizedUser;
import RealEstate.RealEstate;
import RealEstate.Asset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RealEstateTest {

    RealEstate realEstate = RealEstate.getInstance();
    Agent agent = new Agent("Dvir", "Bto");
    Buyer buyer = new Buyer("Ori" , "Amrani");
    Seller seller = new Seller("Lotem", "Bto");

    @Test
    @DisplayName("This function checks if the current user authorized to perform the action")
    void authorize() throws UnAuthorizedUser {
        realEstate.setUser(agent);  // can only update assets
        assertTrue(realEstate.authorize(Role.Agent)); // user is an agent -> true
        assertFalse(realEstate.authorize(Role.Buyer)); // false
        assertFalse(realEstate.authorize(Role.Seller)); // false
    }

    @Test
    void showAssets()
    {
        realEstate.setUser(agent); // not authorized
        assertFalse(realEstate.showAssets());
        realEstate.setUser(buyer); // authorized
        assertTrue(realEstate.showAssets());
        realEstate.setUser(seller); // authorized
        assertTrue(realEstate.showAssets());
    }

    // Important note!
    // This function uses inputs from the user and private functions. Therefore, I will use reflection to access these functions
    @Test
    @DisplayName("This function updates an asset and the file")
    void updateAsset() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // The goal is to access the method that makes sure that the string of the updated asset is valid!

        Method validLine = RealEstate.class.getDeclaredMethod("validLine", String.class);
        validLine.setAccessible(true);
        boolean result = false;
        result = (boolean)validLine.invoke(realEstate, "1,2,-1,660000,true,2,3"); // negative area -> false
        assertFalse(result);
        result = (boolean)validLine.invoke(realEstate, "1,2,a,660000,true,2,3"); // invalid input -> false
        assertFalse(result);
        result = (boolean)validLine.invoke(realEstate, "1,2,60,-1,true,2,3"); // negative price -> false
        assertFalse(result);
        result = (boolean)validLine.invoke(realEstate, "1,2,60,660000,true,2,3,-2"); // inner apartments negative -> false
        assertFalse(result);
        result = (boolean)validLine.invoke(realEstate, "1,2,60,660000,true,2,3"); // true
        assertTrue(result);
    }

    @Test
    @DisplayName("This function removes an asset if the user is seller")
    void removeAsset() throws UnAuthorizedUser {
        realEstate.setUser(agent);
        boolean result = realEstate.removeAsset();
        assertFalse(result);
        realEstate.setUser(buyer);
        result = realEstate.removeAsset();
        assertFalse(result);
    }

    @Test
    @DisplayName("This function notifies an agent(s)")
    void notifyService() throws UnAuthorizedUser {
        // all the tests related to notification service exist in "NotificationTest"!
        // so just make sure the authorization

        realEstate.setUser(agent);
        boolean result = realEstate.notifyService();
        assertFalse(result);
        realEstate.setUser(buyer);
        result = realEstate.notifyService();
        assertFalse(result);
    }

    @Test
    @DisplayName("This function closes a deal")
    void closeDeal() throws UnAuthorizedUser {
        realEstate.setUser(agent);
        boolean result = realEstate.closeDeal();
        assertFalse(result);
        realEstate.setUser(seller);
        result = realEstate.closeDeal();
        assertFalse(result);

       // all the tests related to deal exist in "DealTest"!
    }
}