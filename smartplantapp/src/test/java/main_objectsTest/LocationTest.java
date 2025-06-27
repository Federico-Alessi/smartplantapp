package main_objectsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import smart_plant_app.main_objects.Location;
import smart_plant_app.main_objects.location_factory.IndoorCreator;
import smart_plant_app.main_objects.location_factory.OutdoorCreator;

public class LocationTest {
    IndoorCreator indoor = new IndoorCreator();
    OutdoorCreator outdoor  = new OutdoorCreator();

    @Test
    public void locationDetails() {
        Location location = indoor.createLocation("living room", 6);
        assertNotNull(location);
        assertEquals("living_room", location.getName());
        assertEquals(true, location.getIsIndoor());
        assertEquals(6, location.getSunExposure());
    }

    @Test
    public void locationRemoveComponent() {
        Location location = indoor.createLocation("living room", 6);
        Location balcony = outdoor.createLocation("balcony", 6);
        location.addComponent(balcony);
        assertEquals(1, location.getChildren().size());
        assertTrue(location.getChildren().contains(balcony));
        location.removeComponent(balcony);
        assertEquals(0, location.getChildren().size());
    }
    
}
