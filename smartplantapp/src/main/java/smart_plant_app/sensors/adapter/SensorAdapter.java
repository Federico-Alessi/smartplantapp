package smart_plant_app.sensors.adapter;
import smart_plant_app.sensors.Sensor;

public class SensorAdapter implements Sensor {

    private  final LegacySensor legacySensor; // Instance of the legacy sensor

    /**
     * Constructor for the SensorAdapter class.
     * Initializes the adapter with an instance of the legacy sensor.
     *
     * @param legacySensor The legacy sensor to be adapted.
     */
    public SensorAdapter(LegacySensor legacySensor) {
        this.legacySensor = legacySensor; // Initialize the adapter with the legacy sensor
    }

    @Override
    public float readValue() {
        // Delegate the readValue call to the adapted sensor
        return legacySensor.valueOfReading(); // Call the legacy sensor's method to get the reading
    }
}
