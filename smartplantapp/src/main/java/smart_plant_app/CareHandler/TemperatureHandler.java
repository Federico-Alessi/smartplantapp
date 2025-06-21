package smart_plant_app.careHandler;

import smart_plant_app.main_objects.Plant;

public class TemperatureHandler implements CareHandler {
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
     * Handles the temperature care request for a plant.
     *
     * @param plant The plant to care for.
     */
    @Override
    public void careForPlant(Plant plant) {
        float temperature = plant.readSensor("Thermometer");
        float temperatureDifference = plant.getTemperatureNeeds() - temperature; // actual temperature plant needs

        // Notify user if plant's temperature needs are not met
        if (temperatureDifference > 3) {
            System.out.println(plant.getName() + " is receiving only " + temperature + " degrees. She would be happier if you move her into a place that receives " + plant.getTemperatureNeeds() + " degrees.");
        } else if (temperatureDifference < -3) {
            System.out.println(plant.getName() + " is receiving " + temperature + ". She would be happier if you move her into a place that receives " + plant.getTemperatureNeeds() + " degrees.");
        } else {
            System.out.println(plant.getName() + " is loving this temperature!");
        }

        // If there is a next step, pass the request to it
        if (nextStep != null) {
            nextStep.careForPlant(plant); // If there is a next step, pass the request
        }
    }
}
