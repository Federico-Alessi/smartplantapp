package smart_plant_app.careHandler;

import java.time.Instant;
import java.time.Period;

import smart_plant_app.careHandler.watering.FlowerFertilizer;
import smart_plant_app.careHandler.watering.GreenPlantFertilizer;
import smart_plant_app.careHandler.watering.SucculentFertilizer;
import smart_plant_app.careHandler.watering.Water;
import smart_plant_app.main_objects.Plant;

public class WateringHandler implements CareHandler {
    private CareHandler nextStep; // Next step in the care process

    /**
     * Sets the next step in the care process.
     *
     * @param nextStep The next CareHandler to set.
     */
    @Override
    public void setNextStep(CareHandler nextStep) {
        this.nextStep = nextStep; // Set the next step
    }

    /**
     * Handles the watering care request for a plant.
     *
     * @param plant The plant to care for.
     */
    @Override
    public void careForPlant(Plant plant) {

        boolean isWateringNeeded = plant.getWateringNeeds() > plant.readSensor("Hygrometer"); // True if plant needs watering

        boolean isFertilizationNeeded = false; // Flag to check if fertilization is needed

        try{
            isFertilizationNeeded = plant.getLastFertilized().plus(Period.ofDays(30)).isBefore(Instant.now()); // True if fertilization is needed
        }catch (NullPointerException e){
            isFertilizationNeeded = false; // Fallback to false to avoid overfertilization
            plant.setLastFertilized(); // Set the last fertilization time to now
        }
        // Check if the plant needs watering
        if (isWateringNeeded && isFertilizationNeeded){
            // fertilize plant based on its category
            switch (plant.getCategory()) {
                case SUCCULENT -> {
                    SucculentFertilizer fertilize = new SucculentFertilizer(); // Create a fertilizer instance
                    fertilize.waterPlant(plant);
                }
                case GREENPLANT -> {
                    GreenPlantFertilizer fertilize = new GreenPlantFertilizer(); // Create a fertilizer instance
                    fertilize.waterPlant(plant);
                }
                case FLOWER -> {
                    FlowerFertilizer fertilize = new FlowerFertilizer(); // Create a fertilizer instance
                    fertilize.waterPlant(plant);
                }
                default -> plant.setLastFertilized(); // Update the last fertilization time    
            }
        } else if (isWateringNeeded) {
            Water water = new Water(); // Create a water instance
            water.waterPlant(plant); // Water the plant
        }else {
            System.out.println("The plant does not need watering at this time.");
        }

        // If there is a next step, pass the request to it
        if (nextStep != null) {
            nextStep.careForPlant(plant);
        }
    }
    
}
