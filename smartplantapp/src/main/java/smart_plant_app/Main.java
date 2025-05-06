package smart_plant_app;

import smart_plant_app.main_objects.Plant;
import smart_plant_app.sensors.Hygrometer;

public class Main {
    public static void main(String[] args) {
        // Create a plant object
        Plant pilea = new Plant ("Pilea", "Houseplant", 4, 0.5f);
        Hygrometer pileaHygrometer = new Hygrometer(); // Create a hygrometer for the plant
        
        pilea.showDetails(); // Show details of the plant 
        System.out.println("Hygrometer value is: " + pilea.readSensor(pileaHygrometer));
    }
}