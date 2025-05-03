
import java.util.List;

public class Home {

    interface House {
        public void showDetails();
        public String getName();
    }

    class Location implements House {
        private boolean isIndoor = true;
        private String name = "";
        private String sunExposure = "";
        private List<House> children = null;

        public Location(String name, boolean isIndoor, String sunExposure, List<Home.House> children) {
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

    class Plants implements House {
        private String name = "";
        private String category = "";
        private int sunHoursNeeded = 0;
        private float wateringNeeds = 0.0f;

        public Plants(String name, String category, int sunHoursNeeded, float wateringNeeds) {
            this.name = name;
            this.category = category;
            this.sunHoursNeeded = sunHoursNeeded;
            this.wateringNeeds = wateringNeeds;
        }

        @Override
        public void showDetails() {
            System.out.println("Plant: " + name + ", Category: " + category + ", Sun Hours Needed: " + sunHoursNeeded + ", Soil Humidity Needs: " + wateringNeeds);
        }

        @Override
        public String getName() {
            return name;
        }
    }

}
