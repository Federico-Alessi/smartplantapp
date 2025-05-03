package smart_plant_app.sensers;

public class Hygrometer implements Sensor {
    private float humidity = 0.0f; // default humidity

    @Override
    public float readValue() {
        // Simulate reading the humidity value from a hygrometer sensor
        humidity = (float) (-20 + Math.random() * 31); // Placeholder for actual sensor logic
        return humidity; // Returns humidity value
    }
}
