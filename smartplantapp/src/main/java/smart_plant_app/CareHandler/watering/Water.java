package smart_plant_app.careHandler.watering;

import smart_plant_app.main_objects.Plant;

public class Water implements Watering {

    /**
     * Water the plant.
     * This method prints a message indicating that the plant is being watered.
     * 
     * @param plant The plant to water.
     */
    @Override
    public void waterPlant(Plant plant) {
        String name = plant.getName(); // Get the name of the plant
        System.out.println("Giving a drink to " + name); // Print the watering action
    }
}