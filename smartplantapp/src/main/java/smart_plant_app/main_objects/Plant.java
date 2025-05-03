package smart_plant_app.main_objects;
import smart_plant_app.sensers.Sensor;

public class Plant implements House {
    private String name = "";
    private String category = "";
    private int sunHoursNeeded = 0;
    private float wateringNeeds = 0.0f;

    public Plant(String name, String category, int sunHoursNeeded, float wateringNeeds) {
        this.name = name;
        this.category = category;
        this.sunHoursNeeded = sunHoursNeeded;
        this.wateringNeeds = wateringNeeds;
    }

    @Override
    public void showDetails() {
        System.out.println("Plant: " + name + ", Category: " + category + ", Sun Hours Needed: " + sunHoursNeeded + ", Watering Needs: " + wateringNeeds);
    }

    @Override
    public String getName() {
        return name;
    }

    public float readSensor(Sensor sensor) {
        // Simulate reading a sensor value
        return sensor.readValue(); // Returns the value read from the sensor
    }
    
}