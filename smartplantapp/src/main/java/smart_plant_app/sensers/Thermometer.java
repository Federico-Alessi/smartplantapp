package smart_plant_app.sensers;

public class Thermometer implements Sensor {
    private float temperature = 0.0f; // default temperature

    @Override
    public float readValue() {
        // Simulate reading the temperature value from a thermometer sensor
        temperature = (float) (-20 + Math.random() * 31); // Placeholder for actual sensor logic
        return temperature; // Returns temperature value
    }
}