package smart_plant_app.main_objects;
import java.util.List;

public class Location implements House {
    // Location class implements the House interface and represents a location where plants can be placed.
    private boolean isIndoor = true;
    private String name = "";
    private String sunExposure = "";
    private List<House> children = null;

    public Location(String name, boolean isIndoor, String sunExposure, List<House> children) {
        this.name = name;
        this.isIndoor = isIndoor;
        this.sunExposure = sunExposure;
        this.children = children;
    }

    @Override
    public void showDetails() {
        System.out.println(((isIndoor) ? "Room: " : "Outdoor location: ") + name + ", Sun Exposure: " + sunExposure);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addComponent(House component) {
        children.add(component);
    }

    public void removeComponent(House component) {
        children.remove(component);
    }

    public void showChildren() {
        for (House child : children) {
            child.showDetails();
        }
    }
}
