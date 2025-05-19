package smart_plant_app.careHandler.watering;

import smart_plant_app.main_objects.Plant;

public class SucculentFertilizer implements WaterAndFertilizer{
    
    /**
     * Water the plant.
     * This method prints a message indicating that the plant is being watered.
     *
     * @param plant The plant to water.
     */
    @Override
    public void waterPlant(Plant plant) {
        String name = plant.getName(); // Get the name of the plant
        fertilizePlant(name); // Call the fertilize method
    }

    /**
     * Fertilizes the plant.
     * This method is a placeholder and should be implemented with actual fertilizing logic.
     */
    @Override
    public void fertilizePlant(String name) {
        System.out.println("Giving a boosted drink to " + name + " boosted with succulentt fertilizer"); // Print the fertilizing action
    }
}
