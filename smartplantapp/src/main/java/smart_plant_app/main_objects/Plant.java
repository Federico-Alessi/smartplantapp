package smart_plant_app.main_objects;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import smart_plant_app.sensors.Hygrometer;
import smart_plant_app.sensors.Photometer;
import smart_plant_app.sensors.Sensor;
import smart_plant_app.sensors.Thermometer;

public class Plant implements House{
    //logger import
    private static final Logger logger = Logger.getLogger("globalLogger");

    
    /**
     * Enum representing the categories of plants.
     */
    public enum Categories {
        SUCCULENT, // Succulent category
        FLOWER, // Flower category
        GREENPLANT, // Green plant category
    }; // Category or type of the plant
    
    private String name = ""; // Name of the plant
    private final Categories category; // Category of the plant
    private float sunHoursNeeded = 0; // Number of sunlight hours needed daily
    private float wateringNeeds = 0.0f; // Amount of water needed by the plant
    private float temperatureNeeds = 0.0f; // Temperature needs of the plant
    private final Map<String, Sensor> sensors = new HashMap<>(); // Map to store sensors associated with the plant
    private Instant lastFertilized = null; // Timestamp of the last fertilization
    
    /**
     * Constructor for the Plant class.
     *
     * @param name The name of the plant.
     * @param category The category or type of the plant.
     * @param sunHoursNeeded The number of hours of sunlight the plant needs daily.
     * @param wateringNeeds The amount of water the plant needs.
     * @param temperatureNeeds The temperature needs of the plant.
     */
    public Plant(String name, Categories category, int sunHoursNeeded, float wateringNeeds, float temperatureNeeds) {
        this.name = name; // Initialize the name of the plant
        this.category = category; // Initialize the category of the plant
        this.sunHoursNeeded = sunHoursNeeded; // Initialize the required sunlight hours
        this.wateringNeeds = wateringNeeds; // Initialize the watering needs
        this.temperatureNeeds = temperatureNeeds; // Initialize the temperature needs
    }
    
    /**
     * Connects sensors to the plant. If sensors are already connected, it clears them
     * and reconnects to ensure fresh connections.
     */
    public void connectSensors() {
        // Check if sensors are already connected
        if (!sensors.isEmpty()) { 
            logger.log(Level.CONFIG, "Sensors already connected. Reconnecting...");
            sensors.clear(); // Clear existing sensors
            this.connectSensors(); // Recursively call to reconnect sensors
        } else {
            // Add new sensors to the plant
            sensors.put("Hygrometer", new Hygrometer()); // Connect a Hygrometer sensor
            sensors.put("Thermometer", new Thermometer()); // Connect a Thermometer sensor
            sensors.put("Photometer", new Photometer()); // Connect a Photometer sensor
            logger.log(Level.CONFIG, "Sensors connected: {0}", sensors.keySet()); // Log the connected sensors
        }
    }
    
    /**
     * Displays the details of the plant, including its name, category, sun hours needed, 
     * and watering needs.
     */
    @Override
    public String showDetails() {
        String details ="Plant: " + name + ", Category: " + category + ", Sun Hours Needed: " + sunHoursNeeded + ", Watering Needs: " + wateringNeeds + ", Temperature Needs: " + temperatureNeeds;
        return details;
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
     * Prints the result of showDetails().
     * 
     */
    @Override
    public void printDetails(){
        System.out.println(this.showDetails());
    };
    
    /**
     * Reads the value from a specified sensor. If no sensors are connected, it will
     * automatically connect the sensors before reading the value.
     *
     * @param sensorKey The key of the sensor to read from (e.g., "Hygrometer", "Thermometer", "Photometer").
     * @return The value read from the specified sensor.
     */
    public float readSensor(String sensorKey) {

        Sensor sensor = sensors.get(sensorKey); // Retrieve the sensor associated with the given key

        if (sensor == null) { // If no sensors are connected, connect them
            logger.log(Level.CONFIG, "No sensors connected. Connecting now...");
            this.connectSensors(); // Connect the sensors
            sensor = sensors.get(sensorKey); // Retrieve the sensor again after connecting
            try {
                return sensor.readValue(); // Read the value from the sensor after connecting
            } catch (NullPointerException e) {
                logger.log(Level.SEVERE, "Error connecting the sensor: {0}", e.getMessage());
                return 100f; //returns high moisture level to prevent watering if can't read sensor
            }
        } else {
            return sensor.readValue(); // Read the value from the sensor if already connected
        }
    }
    
    /**
     * Retrieves the sun hours needs of the plant.
     *
     * @return The sun hours needs of the plant.
     */
    public float getSunHoursNeeded() {
        return sunHoursNeeded;
    }
    
    /**
     * Retrieves the watering needs of the plant.
     * 
     * @return The watering needs of the plant.
     */
    public float getWateringNeeds() {
        return wateringNeeds;
    }
    
    /**
     * Retrieves the temperature needs of the plant.
     *
     * @return The temperature needs of the plant.
     */
    public float getTemperatureNeeds() {
        return temperatureNeeds;
    }
    
    /**
     * Retrieves the last fertilization time of the plant.
     *
     * @return The last fertilization time of the plant.
     */
    public Instant getLastFertilized() {
        return lastFertilized;
    }
    
    /**
     * Sets the last fertilization time of the plant to the instant it's called.
     *
     */
    public void setLastFertilized() {
        this.lastFertilized = Instant.now(); // Set the last fertilization time
    }
    
    /**
     * Retrieves the category of the plant.
     *
     * @return The category of the plant.
     */
    public Categories getCategory() {
        return category;
    }
}
