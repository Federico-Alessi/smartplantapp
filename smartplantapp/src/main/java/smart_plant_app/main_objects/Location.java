package smart_plant_app.main_objects;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import smart_plant_app.misc.Misc;

public class Location implements House {
    //logger setup
    private static final Logger logger = Logger.getLogger("globalLogger");

    // Location class implements the House interface and represents a location where plants can be placed.
    private boolean isIndoor = true;
    private String name = "";
    private int sunExposure = 0;
    private final List<House> children = new ArrayList<>();

    /**
     * Constructs a new Location object with the specified attributes.
     *
     * @param name        The name of the location.
     * @param isIndoor    Indicates whether the location is indoors (true) or outdoors (false).
     * @param sunExposure The level of sun exposure for the location (e.g., "Full Sun", "Partial Shade").
     * @param children    A list of House objects associated with this location.
     */
    public Location(String name, boolean isIndoor, int sunExposure) {
        this.name = Misc.sanitize(name);
        this.isIndoor = isIndoor;
        this.sunExposure = sunExposure;
    }

    /**
     * Displays the details of the location, including whether it is indoors or outdoors,
     * its name, and its sun exposure level.
     */
    @Override
    public String showDetails() {
        String details = ((isIndoor) ? "Room: " : "Outdoor location: ") + name + ", Sun Exposure: " + sunExposure;
        return details;
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
     * Prints the result of showDetails().
     * 
     */
    @Override
    public void printDetails(){
        System.out.println(this.showDetails());
        this.printChildren();
    };

    /**
     * Adds a House component to the list of children associated with this Location.
     *
     * @param component the House object to be added as a child component
     */
    public void addComponent(House component) {
        children.add(component);
        logger.log(Level.CONFIG, "{0} added to {1}", new Object[]{component.getName(), this.getName()});
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
     * Prints all childs of the location.
     * Iterates through the list of children and prints their name
     */
    public void printChildren() {
        if (children.isEmpty()) {
            logger.log(Level.INFO, "{0} is empty.", this.getName());
            return;
        }

        System.out.println("Cildren:");
        for (House child : children) {
            System.out.println(" -" + child.getName());
        }
    }
}
