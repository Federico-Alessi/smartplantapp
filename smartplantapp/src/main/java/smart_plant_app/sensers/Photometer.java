package smart_plant_app.sensers;

public class Photometer implements Sensor {
    private float lightIntensity = 0.0f; // default light intensity

    @Override
    public float readValue() {
        // Simulate reading the light intensity value from a photometer sensor
        lightIntensity = (float) (-20 + Math.random() * 31); // Placeholder for actual sensor logic
        return lightIntensity; // Returns light intensity value
    } 
}