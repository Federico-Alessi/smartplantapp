package smart_plant_app.main_objects.location_factory;
import smart_plant_app.main_objects.Location;

public class OutdoorCreator implements LocationFactory{

    /**
     * Method to create an outdoor location
     * 
     * @param name The name of the location
     * @param sunExposure The daily sun exposure in hours
     */
    @Override
    public Location createLocation(String name, int sunExposure) {
        Location location = new Location(name, false, sunExposure);
        return location;
    }
    
}
