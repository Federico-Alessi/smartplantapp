package main_objectsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import smart_plant_app.main_objects.Location;

public class LocationTest {

    @Test
    public void locationDetails() {
        Location location = new Location("livingroom", true, 6);
        assertNotNull(location);
        assertEquals("livingroom", location.getName());

        Location sub = new Location("subroom", true, 6);
        location.addComponent(sub);

    }
    
}
