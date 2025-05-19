package smart_plant_app.main_objects;

import smart_plant_app.main_objects.Plant.Categories;

public class PlantFactory {
    /**
     * Creates a new Plant object with the specified parameters.
     *
     * @param name The name of the plant.
     * @param category The category or type of the plant.
     * @return A new Plant object with the specified parameters.
     */
    public static Plant createPlant(String name, Categories category) {
        try {
            switch (category) {
                case SUCCULENT -> {
                    System.out.println("Creating a succulent plant.");
                    return new Plant(name, category, 6, 10.0f, 25.0f);
                }
                case FLOWER -> {
                    System.out.println("Creating a flower plant.");
                    return new Plant(name, category, 10, 80.0f, 20.0f);
                }
                case GREENPLANT -> {
                    System.out.println("Creating a green plant.");
                    return new Plant(name, category, 8, 65.0f, 21.0f);
                }
                default -> throw new IllegalArgumentException("Unknown category: " + category);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating plant: " + e.getMessage());
            return null; // Return null if an error occurs
        }
    }
}
