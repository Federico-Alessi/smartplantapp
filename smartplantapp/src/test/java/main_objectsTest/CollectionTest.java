package main_objectsTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import smart_plant_app.main_objects.Collection;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class CollectionTest {
    
    @Test
    public void collectionCreation(){
        Collection<Plant> collection = new Collection<>(" /test collection ");
        assertEquals("test_collection",collection.getFilename());
    }

    @Test
    public void collectionAddAndRemove() throws IOException {
        Collection<Plant> plants = new Collection<>("plants");
        Plant pilea = PlantFactory.createPlant("pilea", Plant.Categories.GREENPLANT);
        plants.addElement(pilea);

        //add
        Path path = plants.getPath();
        List<String> lines = Files.readAllLines(path);
        assertTrue(lines.contains(pilea.showDetails()));

        //remove
        /**adding second plant because if only 1 plant in collection
         * removing it causes file deletion
         * */
        Plant cactus = PlantFactory.createPlant("cactus", Plant.Categories.SUCCULENT);
        plants.addElement(cactus);
        plants.removeElement(pilea);
        lines = Files.readAllLines(path);
        assertFalse(lines.contains(pilea.showDetails()));
        Files.delete(path);
    }
}