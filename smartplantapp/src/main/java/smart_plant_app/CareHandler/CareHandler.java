package smart_plant_app.careHandler;

import smart_plant_app.main_objects.Plant;

public interface CareHandler {

    void careForPlant(Plant plant); // Handle the care request
    void setNextStep(CareHandler nextStep); // Set the next step in the care process
}
