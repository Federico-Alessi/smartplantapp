package smart_plant_app.sensors;

public class Photometer implements Sensor {
    private float lightHours = 0.0f; // default light intensity

    /**
     * Reads the light intensity value from the photometer sensor.
     *
     * @return The light intensity value read from the sensor.
     */
    @Override
    public float readValue() {
        // Simulate reading the sun exposure time in hours
        lightHours = (float) Math.floor(Math.random() * 24);
        return lightHours;
    }
}