package main_objectsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;


public class PlantFactoryTest {
    
    @Test
    public void testCreatePlant_Success() {

        Plant plant = PlantFactory.createPlant("Pilea", Plant.Categories.GREENPLANT);
        assertNotNull(plant);
        assertEquals(plant.getName(), "Pilea");
        assertEquals(plant.getCategory(), Plant.Categories.GREENPLANT);
    }
}
