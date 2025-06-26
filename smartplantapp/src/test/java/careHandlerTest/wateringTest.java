package careHandlerTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import smart_plant_app.careHandler.WateringHandler;
import smart_plant_app.careHandler.watering.GreenPlantFertilizer;
import smart_plant_app.careHandler.watering.Water;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class wateringTest {
    Plant pilea=PlantFactory.createPlant("pilea peperomioide", Plant.Categories.GREENPLANT);
    
    @Test
    public void wateringHandlerTest() {
        WateringHandler waterhandler= new WateringHandler();
        assertDoesNotThrow(()->waterhandler.careForPlant(pilea));
    }

    @Test
    public void waterTest() {
        Water water = new Water();
        assertDoesNotThrow(()->water.waterPlant(pilea));
    }

    @Test
    public void waterAndFertilizerTest() {
        GreenPlantFertilizer fertilize = new GreenPlantFertilizer();
        assertNotNull(fertilize);
        assertDoesNotThrow(()->fertilize.waterPlant(pilea));
    }
}
