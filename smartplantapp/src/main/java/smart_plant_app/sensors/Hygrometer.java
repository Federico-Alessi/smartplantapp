package smart_plant_app.sensors;

public class Hygrometer implements Sensor {
    private float humidity = 0.0f; // default humidity

    @Override
    public float readValue() {
        // Placeholder for actual sensor reading logic
        float rawHumidity = (float) (Math.random() * 101); // Humidity values range from 0 to 100
        humidity = Math.round(rawHumidity * 10) / 10.0f; // Round to 1 decimal place
        return humidity; // Return humidity with 1 digit after the decimal point
    }
}
