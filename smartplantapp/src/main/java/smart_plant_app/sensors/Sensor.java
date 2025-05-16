package smart_plant_app.sensors;

public interface Sensor {
    /**
     * Reads the value from the sensor.
     *
     * @return the hours of sun exposure in the last 24 hours.
     */
    float readValue(); // Method to read the sensor value
}
