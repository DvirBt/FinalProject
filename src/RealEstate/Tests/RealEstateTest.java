package RealEstate.Tests;

import static org.junit.jupiter.api.Assertions.*;

import RealEstate.Entities.Agent;
import RealEstate.Entities.Buyer;
import RealEstate.RealEstate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.Area;

public class RealEstateTest {

    RealEstate realEstate;

    void init()
    {
        realEstate = RealEstate.getInstance();
    }

    @Test
    @DisplayName("Login to the system")
    void login()
    {
        init();

        realEstate.login();
    }

    @Test
    void updateFile()
    {

    }

    @Test
    @DisplayName("Check if the user is authorized to perform an action")
    void authorize()
    {
        init();
        Buyer buyer = new Buyer("Dvir", "Bto");
        //realEstate.authorize("");
    }

    @Test
    void updateAsset() {
    }

    @Test
    @DisplayName("Show all assets")
    void showAssets()
    {
        init();
        //realEstate.
    }

    @Test
    void removeAsset() {
    }

    @Test
    void addAsset() {
    }

    @Test
    void pullAssets() {
    }

    @Test
    void selectAssetByNum() {
    }

    @Test
    void closeDeal() {
    }


}
