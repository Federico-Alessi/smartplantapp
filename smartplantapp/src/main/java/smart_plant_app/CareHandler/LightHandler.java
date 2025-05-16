package smart_plant_app.careHandler;

import smart_plant_app.main_objects.Plant;

public class LightHandler implements CareHandler {
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
     * Handles the light care request for a plant.
     *
     * @param plant The plant to care for.
     */
    @Override
    public void careForPlant(Plant plant) {
        float lightDifference = plant.getSunHoursNeeded() - plant.readSensor("LightSensor"); // Calculate the difference in light needs
        // Check if the plant needs more light
        if (lightDifference > 3) {
            System.out.println(plant.getName() + " is receiveing only " + plant.readSensor("LightSensor") + " hours of light. She would be happier if you move her into a palce that receives " + plant.getSunHoursNeeded() + " hours of light.");
        } else if (lightDifference < -3) {
            System.out.println(plant.getName() + " is receiving too much light. She would be happier if you move her into a place that receives " + plant.getSunHoursNeeded() + " hours of light.");
        } else {
            System.out.println(plant.getName() + " is receiving the right amount of light. She is happy!");
        }

        // If there is a next step, pass the request to it
        if (nextStep != null) {
            nextStep.careForPlant(plant); // If there is a next step, pass the request
        }
    }
}