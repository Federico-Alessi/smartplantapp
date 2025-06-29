package smart_plant_app.sensors;

public class Hygrometer implements Sensor {
    private float humidity = 0.0f; // default humidity

    /**
     * Reads the humidity value from the hygrometer sensor.
     *
     * @return The humidity value read from the sensor.
     */
    @Override
    public float readValue() {
        // Simulate reading the humidity value from a hygrometer sensor
        humidity = (float) Math.floor(Math.random() * 101);
        return humidity;
    }
}
