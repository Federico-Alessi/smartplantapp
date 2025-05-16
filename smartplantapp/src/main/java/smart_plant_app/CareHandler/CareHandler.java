package smart_plant_app.CareHandler;

import smart_plant_app.main_objects.Plant;

public interface CareHandler {

    void setNextStep(CareHandler nextStep); // Set the next step in the care process
    void careForPlant(Plant plant); // Handle the care request
}
