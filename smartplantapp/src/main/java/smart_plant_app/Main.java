package smart_plant_app;

import smart_plant_app.main_objects.Plant;

public class Main {
    public static void main(String[] args) {
        // Create a plant object
        Plant pilea = new Plant ("Pilea", "Houseplant", 4, 0.5f);

        pilea.ConnectSensors();
        System.out.println(pilea.readSensor("Hygrometer"));
        pilea.ConnectSensors();
        System.out.println(pilea.readSensor("Hygrometer"));
    }
}