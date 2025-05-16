package smart_plant_app.careHandler;

import java.time.Instant;

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
        boolean isWateringNeeded = plant.getWateringNeeds() > plant.readSensor("Hygrometer"); // Flag to check if watering is needed
        boolean isFertilizationNeeded = plant.getLastFertilized().plus(java.time.Period.ofDays(30)).isBefore(Instant.now()); // Flag to check if fertilization is needed
        // Check if the plant needs watering
        if (isWateringNeeded && isFertilizationNeeded){
            System.out.println("Fertilizing the plant: " + plant.getName());
            // TODO add fertilization decorator
            plant.setLastFertilized(Instant.now()); // Update the last fertilization time 
        } else if (isWateringNeeded) {
            System.out.println("Watering the plant: " + plant.getName());
            // Logic to fertilize the plant
        }else {
            System.out.println("The plant does not need watering at this time.");
        }

        // If there is a next step, pass the request to it
        if (nextStep != null) {
            nextStep.careForPlant(plant);
        }
    }
    
}
