package main_objectsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import smart_plant_app.main_objects.Location;

public class LocationTest {

    @Test
    public void locationDetails() {
        Location location = new Location("livingroom", true, 6);
        assertNotNull(location);
        assertEquals("livingroom", location.getName());
        assertEquals(true, location.getIsIndoor());
        assertEquals(6, location.getSunExposure());
    }

    @Test
    public void locationRemoveComponent() {
        Location location = new Location("livingroom", true, 6);
        Location sub = new Location("subroom", true, 6);
        location.addComponent(sub);
        assertEquals(1, location.getChildren().size());
        assertTrue(location.getChildren().contains(sub));
        location.removeComponent(sub);
        assertEquals(0, location.getChildren().size());
    }
    
}
