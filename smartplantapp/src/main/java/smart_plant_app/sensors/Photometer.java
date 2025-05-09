package smart_plant_app.sensors;

public class Photometer implements Sensor {
    private float lightIntensity = 0.0f; // default light intensity

    @Override
    public float readValue() {
        // Simulate reading the light intensity value from a photometer sensor
        lightIntensity = (float) (100 + Math.random() * 99_900); // Values between 100 and 100,000 lux
        return Math.round(lightIntensity * 10) / 10.0f; // Rounded to 1 decimal place
    } 
}