package smart_plant_app;

import smart_plant_app.careHandler.LightHandler;
import smart_plant_app.careHandler.TemperatureHandler;
import smart_plant_app.careHandler.WateringHandler;
import smart_plant_app.main_objects.Collection;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        String b = "ciao";
        Plant pilea = PlantFactory.createPlant("Pilea", Plant.Categories.GREENPLANT);
        Plant cactus = PlantFactory.createPlant("Cacta", Plant.Categories.SUCCULENT);

        System.out.println(pilea.readSensor("Hygrometer"));

        WateringHandler wateringHandler = new WateringHandler();
        TemperatureHandler temperatureHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();
        
        wateringHandler.setNextStep(temperatureHandler);
        temperatureHandler.setNextStep(lightHandler);
        wateringHandler.careForPlant(pilea);

        Collection<Plant> collection = new Collection<>();
        collection.displayElements();
        
    }
}