package smart_plant_app.main_objects;
import java.util.Map;

import smart_plant_app.sensors.Hygrometer;
import smart_plant_app.sensors.Photometer;
import smart_plant_app.sensors.Sensor;
import smart_plant_app.sensors.Thermometer;

public class Plant implements House {
    private String name = "";
    private String category = "";
    private int sunHoursNeeded = 0;
    private float wateringNeeds = 0.0f;
    private Map<String, Sensor> sensors; // List of sensors associated with the plant

    public Plant(String name, String category, int sunHoursNeeded, float wateringNeeds) {
        this.name = name;
        this.category = category;
        this.sunHoursNeeded = sunHoursNeeded;
        this.wateringNeeds = wateringNeeds;
        this.sensors = new java.util.HashMap<>(); // Initialize the sensors map
    }

    public void ConnectSensors() {
        // Connect sensors to the plant
        if (!sensors.isEmpty()) { //if sensors exist, clear them and reconnect
            System.out.println("Sensors already connected. Reconnecting...");
            sensors.clear(); // Clear existing sensors if any
            this.ConnectSensors();
        } else {
        sensors.put("Hygrometer", new Hygrometer());
        sensors.put("Thermometer", new Thermometer());
        sensors.put("Photometer", new Photometer());
        }
    }

    @Override
    public void showDetails() {
        System.out.println("Plant: " + name + ", Category: " + category + ", Sun Hours Needed: " + sunHoursNeeded + ", Watering Needs: " + wateringNeeds);
    }

    @Override
    public String getName() {
        return name;
    }

    public float readSensor(String sensorKey) {
        Sensor sensor = sensors.get(sensorKey);
        if (sensors.isEmpty()) { //if no sensors, connect them
            System.out.println("No sensors connected. Connecting now...");
            this.ConnectSensors();
            return sensor.readValue();
        } else {
            return sensor.readValue();
        }
    }
    
}