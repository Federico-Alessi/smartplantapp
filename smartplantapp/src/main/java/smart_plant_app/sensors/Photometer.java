package smart_plant_app.sensors;

public class Photometer implements Sensor {
    private float lightIntensity = 0.0f; // default light intensity

    /**
     * Reads the light intensity value from the photometer sensor.
     *
     * @return The light intensity value read from the sensor.
     */
    @Override
    public float readValue() {
        // Simulate reading the sun exposure time in hours
        lightIntensity = Math.round((100 + Math.random() * 25) * 10) / 10.0f;
        return lightIntensity;
    }
}