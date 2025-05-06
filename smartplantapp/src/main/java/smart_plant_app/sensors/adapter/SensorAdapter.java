package smart_plant_app.sensors.adapter;
import smart_plant_app.sensors.Sensor;

public class SensorAdapter implements Sensor {

    private  final LegacySensor legacySensor; // Instance of the legacy sensor

    public SensorAdapter(LegacySensor legacySensor) {
        this.legacySensor = legacySensor; // Initialize the adapter with the legacy sensor
    }

    @Override
    public float readValue() {
        // Delegate the readValue call to the adapted sensor
        return legacySensor.valueOfReading(); // Call the legacy sensor's method to get the reading
    }
}
