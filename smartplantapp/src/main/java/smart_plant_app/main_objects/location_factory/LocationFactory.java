package smart_plant_app.main_objects.location_factory;
import smart_plant_app.main_objects.Location;

public interface LocationFactory {
    //method to create locations
    public Location createLocation(String name, int sunExposure);
}
