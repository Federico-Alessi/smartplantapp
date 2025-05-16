package smart_plant_app;

import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class Main {
    public static void main(String[] args) {

        Plant pilea = PlantFactory.createPlant("Pilea", Plant.Category.GREENPLANT);

        pilea.connectSensors();
        System.out.println(pilea.readSensor("Hygrometer"));
        pilea.connectSensors();
        System.out.println(pilea.readSensor("Hygrometer"));
        System.out.println(pilea.readSensor("Thermometer"));
        System.out.println(pilea.readSensor("Photometer"));
    }
}