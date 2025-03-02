package RealEstate.Tests;

import RealEstate.AdditionalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalServiceTest {
    @Test
    @DisplayName("Returns the name of the service")
    void getServiceType()
    {
        AdditionalService service = AdditionalService.Guarantee;
        assertEquals("Guarantee", service.getServiceType());
        service = AdditionalService.Clean;
        assertEquals("Clean", service.getServiceType());
        service = AdditionalService.Transfer;
        assertEquals("Transfer", service.getServiceType());
        service = AdditionalService.Decorate;
        assertEquals("Decorate", service.getServiceType());
    }

    @Test
    void getCost()
    {
        AdditionalService service = AdditionalService.Guarantee;
        assertEquals(1000, service.getCost());
        service = AdditionalService.Clean;
        assertEquals(2500, service.getCost());
        service = AdditionalService.Transfer;
        assertEquals(5000, service.getCost());
        service = AdditionalService.Decorate;
        assertEquals(15000, service.getCost());
    }
}