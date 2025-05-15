package smart_plant_app.main_objects;
import java.util.List;

public class Location implements House {
    // Location class implements the House interface and represents a location where plants can be placed.
    private boolean isIndoor = true;
    private String name = "";
    private String sunExposure = "";
    private List<House> children = null;

    /**
     * Constructs a new Location object with the specified attributes.
     *
     * @param name        The name of the location.
     * @param isIndoor    Indicates whether the location is indoors (true) or outdoors (false).
     * @param sunExposure The level of sun exposure for the location (e.g., "Full Sun", "Partial Shade").
     * @param children    A list of House objects associated with this location.
     */
    public Location(String name, boolean isIndoor, String sunExposure, List<House> children) {
        this.name = name;
        this.isIndoor = isIndoor;
        this.sunExposure = sunExposure;
        this.children = children;
    }

    /**
     * Displays the details of the location, including whether it is indoors or outdoors,
     * its name, and its sun exposure level.
     */
    @Override
    public void showDetails() {
        System.out.println(((isIndoor) ? "Room: " : "Outdoor location: ") + name + ", Sun Exposure: " + sunExposure);
    }

    /**
     * Retrieves the name of the location.
     *
     * @return The name of the location.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Adds a House component to the list of children associated with this Location.
     *
     * @param component the House object to be added as a child component
     */
    public void addComponent(House component) {
        children.add(component);
    }

    /**
     * Removes a specified component (House) from the list of children associated 
     * with this Location.
     *
     * @param component the House object to be removed from the children list
     */
    public void removeComponent(House component) {
        children.remove(component);
    }

    /**
     * Displays the details of all child houses associated with this location.
     * Iterates through the list of child houses and calls their respective
     * showDetails method to display their information.
     */
    public void showChildren() {
        for (House child : children) {
            child.showDetails();
        }
    }
}
