package smart_plant_app.sensers.adapter;

public class LegacySensor {

    public float valueOfReading() {
        // Simulate reading the value from a legacy sensor
        float reading = (float) (-20 + Math.random() * 31); // Placeholder for actual sensor logic
        return reading; // Returns the reading value
    }
}
