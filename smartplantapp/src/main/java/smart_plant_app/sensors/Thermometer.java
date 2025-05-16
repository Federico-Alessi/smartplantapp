package smart_plant_app.sensors;

public class Thermometer implements Sensor {
    private float temperature = 0.0f; // default temperature

    /**
     * Reads the temperature value from the thermometer sensor.
     *
     * @return The temperature value read from the sensor.
     */
    @Override
    public float readValue() {
        // Simulate reading the temperature value from a thermometer sensor
        temperature = Math.round((-20 + Math.random() * 31) * 10) / 10.0f;
        return temperature; // Returns temperature value
    }
}