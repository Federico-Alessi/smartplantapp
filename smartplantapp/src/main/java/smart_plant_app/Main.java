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
    private static final Logger logger = Logger.getLogger("");
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
        Plant pilea = PlantFactory.createPlant("Pilea peperomioide", Plant.Categories.GREENPLANT);
        Plant cactus = PlantFactory.createPlant("Cactus", Plant.Categories.SUCCULENT);
        Plant gaillardia = PlantFactory.createPlant("gaillardia", Plant.Categories.FLOWER);

        //System.out.println(pilea.readSensor("Hygrometer"));

        WateringHandler wateringHandler = new WateringHandler();
        TemperatureHandler temperatureHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();

        wateringHandler.setNextStep(temperatureHandler);
        temperatureHandler.setNextStep(lightHandler);
        wateringHandler.careForPlant(pilea);

        Collection<Plant> collection = new Collection<>("personal_list");
        collection.addElement(pilea);
        collection.addElement(cactus);
        collection.addElement(gaillardia);
        // collection.displayElements();
        // collection.removeElement(pilea);
        // collection.displayElements();

        Location living = new Location("livingroom", true, 6);
        living.printDetails();
        Location balcony = new Location("balcony", false, 6);
        System.out.println(balcony.showDetails());
        living.addComponent(balcony);
        living.addComponent(pilea);
        balcony.addComponent(gaillardia);
        
        living.printDetails();
    }
}