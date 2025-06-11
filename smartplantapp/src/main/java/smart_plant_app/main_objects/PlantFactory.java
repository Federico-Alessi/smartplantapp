package smart_plant_app.main_objects;

import java.util.logging.Level;
import java.util.logging.Logger;

import smart_plant_app.main_objects.Plant.Categories;
import smart_plant_app.misc.Misc;

public class PlantFactory {
    private static final Logger logger = Logger.getLogger("globalLogger");

    /**
     * Creates a new Plant object with the specified parameters.
     *
     * @param name The name of the plant.
     * @param category The category or type of the plant.
     * @return A new Plant object with the specified parameters.
     */
    public static Plant createPlant(String name, Categories category) {
        String sanitizedName = Misc.sanitize(name);

        try {
            switch (category) {
                case SUCCULENT -> {
                    logger.log(Level.CONFIG, "Creating a succulent plant.");
                    return new Plant(sanitizedName, category, 6, 10.0f, 25.0f);
                }
                case FLOWER -> {
                    logger.log(Level.CONFIG, "Creating a flower plant.");
                    return new Plant(sanitizedName, category, 10, 80.0f, 20.0f);
                }
                case GREENPLANT -> {
                    logger.log(Level.CONFIG, "Creating a green plant.");
                    return new Plant(sanitizedName, category, 8, 65.0f, 21.0f);
                }
                default -> throw new IllegalArgumentException("Unknown category: " + category);
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error creating plant: {0}", e.getMessage());
            return null; // Return null if an error occurs
        }
    }
}
