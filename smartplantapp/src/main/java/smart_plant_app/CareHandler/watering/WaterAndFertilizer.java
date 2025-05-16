package smart_plant_app.careHandler.watering;

import smart_plant_app.main_objects.Plant;

public interface WaterAndFertilizer extends Watering {
    
    @Override
    void waterPlant(Plant plant); // Method to water the plant
    
    void fertilizePlant(String name); // Method to fertilize the plant
}
