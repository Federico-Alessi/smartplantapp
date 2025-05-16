package smart_plant_app.main_objects;

import smart_plant_app.main_objects.Plant.Category;

public class PlantFactory {
    /**
     * Creates a new Plant object with the specified parameters.
     *
     * @param name The name of the plant.
     * @param category The category or type of the plant.
     * @return A new Plant object with the specified parameters.
     */
    public static Plant createPlant(String name, Category category) {
        switch (category) {
            case SUCCULENT -> {
                System.out.println("Creating a succulent plant.");
                return new Plant(name, category, 6, 0.2f, 20.0f);
            }
            case HERB -> {
                System.out.println("Creating an herb plant.");
                return new Plant(name, category, 8, 0.3f, 22.0f);
            }
            case FLOWER -> {
                System.out.println("Creating a flower plant.");
                return new Plant(name, category, 10, 0.4f, 24.0f);
            }
            case TREE -> {
                System.out.println("Creating a tree plant.");
                return new Plant(name, category, 12, 0.5f, 18.0f);
            }
            case GREENPLANT -> {
                System.out.println("Creating a green plant.");
                return new Plant(name, category, 8, 0.3f, 22.0f);
            }
            case PALM -> {
                System.out.println("Creating a palm plant.");
                return new Plant(name, category, 10, 0.4f, 25.0f);
            }

            default -> throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
