package smart_plant_app;

import smart_plant_app.careHandler.LightHandler;
import smart_plant_app.careHandler.TemperatureHandler;
import smart_plant_app.careHandler.WateringHandler;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class Main {
    public static void main(String[] args) {

        Plant pilea = PlantFactory.createPlant("Pilea", Plant.Categories.GREENPLANT);

        System.out.println(pilea.readSensor("Hygrometer"));

        WateringHandler wateringHandler = new WateringHandler();
        TemperatureHandler temperatureHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();
        
        wateringHandler.setNextStep(temperatureHandler);
        temperatureHandler.setNextStep(lightHandler);
        wateringHandler.careForPlant(pilea);
    }
}