package smart_plant_app.main_objects;
import java.util.Map;

import smart_plant_app.sensors.Hygrometer;
import smart_plant_app.sensors.Photometer;
import smart_plant_app.sensors.Sensor;
import smart_plant_app.sensors.Thermometer;

public class Plant implements House {
    private String name = ""; // Name of the plant
    private String category = ""; // Category or type of the plant
    private int sunHoursNeeded = 0; // Number of sunlight hours needed daily
    private float wateringNeeds = 0.0f; // Amount of water needed by the plant
    private final Map<String, Sensor> sensors; // Map to store sensors associated with the plant

    /**
     * Constructor for the Plant class.
     *
     * @param name The name of the plant.
     * @param category The category or type of the plant.
     * @param sunHoursNeeded The number of hours of sunlight the plant needs daily.
     * @param wateringNeeds The amount of water the plant needs.
     */
    public Plant(String name, String category, int sunHoursNeeded, float wateringNeeds) {
        this.name = name; // Initialize the name of the plant
        this.category = category; // Initialize the category of the plant
        this.sunHoursNeeded = sunHoursNeeded; // Initialize the required sunlight hours
        this.wateringNeeds = wateringNeeds; // Initialize the watering needs
        this.sensors = new java.util.HashMap<>(); // Initialize the sensors map
    }

    /**
     * Connects sensors to the plant. If sensors are already connected, it clears them
     * and reconnects to ensure fresh connections.
     */
    public void ConnectSensors() {
        // Check if sensors are already connected
        if (!sensors.isEmpty()) { 
            System.out.println("Sensors already connected. Reconnecting...");
            sensors.clear(); // Clear existing sensors
            this.ConnectSensors(); // Recursively call to reconnect sensors
        } else {
            // Add new sensors to the plant
            sensors.put("Hygrometer", new Hygrometer()); // Connect a Hygrometer sensor
            sensors.put("Thermometer", new Thermometer()); // Connect a Thermometer sensor
            sensors.put("Photometer", new Photometer()); // Connect a Photometer sensor
        }
    }

    /**
     * Displays the details of the plant, including its name, category, sun hours needed, 
     * and watering needs.
     */
    @Override
    public void showDetails() {
        System.out.println("Plant: " + name + ", Category: " + category + ", Sun Hours Needed: " + sunHoursNeeded + ", Watering Needs: " + wateringNeeds);
    }

    /**
     * Retrieves the name of the plant.
     *
     * @return The name of the plant.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Reads the value from a specified sensor. If no sensors are connected, it will
     * automatically connect the sensors before reading the value.
     *
     * @param sensorKey The key of the sensor to read from (e.g., "Hygrometer", "Thermometer", "Photometer").
     * @return The value read from the specified sensor.
     */
    public float readSensor(String sensorKey) {
        Sensor sensor = sensors.get(sensorKey); // Retrieve the sensor associated with the given key
        if (sensors.isEmpty()) { // If no sensors are connected, connect them
            System.out.println("No sensors connected. Connecting now...");
            this.ConnectSensors(); // Connect the sensors
            return sensor.readValue(); // Read the value from the sensor after connecting
        } else {
            return sensor.readValue(); // Read the value from the sensor if already connected
        }
    }
}
