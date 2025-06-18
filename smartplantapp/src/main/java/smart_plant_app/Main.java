package smart_plant_app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import smart_plant_app.careHandler.LightHandler;
import smart_plant_app.careHandler.TemperatureHandler;
import smart_plant_app.careHandler.WateringHandler;
import smart_plant_app.main_objects.Collection;
import smart_plant_app.main_objects.Location;
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class Main {
    public static final Logger logger = Logger.getLogger("");
    private static final Path loggingPath = Paths.get("smartplantapp", "src", "main", "resources", "logs", "logs.log");

    public static void main(String[] args) {

        // setup global logger
        logger.setLevel(Level.ALL);
        // Ensure no duplicate handlers are attached
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        // setup console logger for all levels
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
        // setup file logger for severe logs
        try {
            FileHandler fileHandler = new FileHandler(loggingPath.toString(), 10000, 5, true);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException | SecurityException e) {
            System.err.println(e.getMessage());
        }
        logger.log(Level.CONFIG, "Welcome to smartplantapp");

        // actual start of application
        Plant pilea = PlantFactory.createPlant("Pilina", Plant.Categories.GREENPLANT);
        Plant cactus = PlantFactory.createPlant("Cacta", Plant.Categories.SUCCULENT);
        Plant c = PlantFactory.createPlant("pothos", Plant.Categories.GREENPLANT);

        System.out.println(pilea.readSensor("Hygrometer"));

        WateringHandler wateringHandler = new WateringHandler();
        TemperatureHandler temperatureHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();

        wateringHandler.setNextStep(temperatureHandler);
        temperatureHandler.setNextStep(lightHandler);
        wateringHandler.careForPlant(pilea);

        Collection<Plant> collection = new Collection<>("personal_list");
        collection.addElement(pilea);
        collection.addElement(cactus);
        collection.addElement(c);
        // collection.displayElements();
        // collection.removeElement(pilea);
        // collection.displayElements();

        Location location = new Location("livingroom", true, 6);
        location.printDetails();
        Location sub = new Location("subroom", true, 6);
        System.out.println(sub.showDetails());
        location.addComponent(sub);
        location.printDetails();
    }
}