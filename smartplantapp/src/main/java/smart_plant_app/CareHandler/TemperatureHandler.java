package smart_plant_app.careHandler;

import smart_plant_app.main_objects.Plant;

public class TemperatureHandler {
    private CareHandler nextStep; // Next step in the care process

    /**
     * Sets the next step in the care process.
     *
     * @param nextStep The next CareHandler to set.
     */
    public void setNextStep(CareHandler nextStep) {
        this.nextStep = nextStep; // Set the next step
    }

    /**
     * Handles the temperature care request for a plant.
     *
     * @param plant The plant to care for.
     */
    public void careForPlant(Plant plant) {
        float temperatureDifference = plant.getTemperatureNeeds() - plant.readSensor("TemperatureSensor"); // Calculate the difference in temperature needs
        // Check if the plant needs more light
        if (temperatureDifference > 3) {
            System.out.println(plant.getName() + " is receiving only " + plant.readSensor("TemperatureSensor") + " degrees. She would be happier if you move her into a place that receives " + plant.getTemperatureNeeds() + " degrees.");
        } else if (temperatureDifference < -3) {
            System.out.println(plant.getName() + " is receiving too much light. She would be happier if you move her into a place that receives " + plant.getTemperatureNeeds() + " degrees.");
        } else {
            System.out.println(plant.getName() + " is receiving the right amount of light. She is happy!");
        }

        // If there is a next step, pass the request to it
        if (nextStep != null) {
            nextStep.careForPlant(plant); // If there is a next step, pass the request
        }
    }
}
