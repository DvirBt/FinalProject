package RealEstate.Tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest extends BaseStats{

    @Test
    @DisplayName("This test checks if a given asset exists in this building")
    void isExist()
    {
        assertFalse(building.isExist(asset2)); // asset 2 not in this building
        assertTrue(building.isExist(asset)); // asset is in this building
        building.add(asset2); // add asset2
        assertTrue(building.isExist(asset2)); // asset2 now in this building
        building.remove(asset2); // remove asset2
        assertFalse(building.isExist(asset2)); // asset2 now not in this building
    }

    @Test
    void testToString()
    {
        String expected;
        //assertEquals(expected, );
    }
}