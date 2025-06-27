package sensorsTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import smart_plant_app.careHandler.LightHandler;
import smart_plant_app.careHandler.TemperatureHandler;
import smart_plant_app.careHandler.WateringHandler;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class sensorTest {
    
    //plant to perform tests on
    Plant pilea = PlantFactory.createPlant("pilea", Plant.Categories.GREENPLANT);

    @Test
    public void hygrometerTest() {
        for (int i = 101; i>0; i--){
            float reading = pilea.readSensor("Hygrometer");
            assertTrue(0<=reading && reading<=100);
        }
    }

    @Test
    public void thermometerTest() {
        for (int i = 1000; i>=0; i--) {
            float reading = pilea.readSensor("Thermometer");
            assertTrue(-20<=reading && reading<41);
        }
    }

    @Test
    public void photometerTest() {
        for (int i = 1000; i>=0; i--) {
            float reading = pilea.readSensor("Photometer");
            assertTrue(0<=reading && reading<24);
        }
    }

    @Test
    public void chainTest() {
        WateringHandler waterHandler = new WateringHandler();
        TemperatureHandler tempHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();

        waterHandler.setNextStep(tempHandler);
        tempHandler.setNextStep(lightHandler);
        assertDoesNotThrow(() -> waterHandler.careForPlant(pilea));
    }
}
