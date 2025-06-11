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
import smart_plant_app.main_objects.Plant;
import smart_plant_app.main_objects.PlantFactory;

public class Main {
    private static final Logger logger = Logger.getLogger("globalLogger");
    private static final Path loggingPath = Paths.get("smartplantapp", "src", "main", "resources", "logs", "logs.log");
    
    public static void main(String[] args) {

        //setup global logger
        logger.setLevel(Level.ALL);
        logger.log(Level.CONFIG, "Welcome to smartplantapp");
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        //setup console logger for all levels
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        //setup file logger for severe logs
        try{
            FileHandler fileHandler = new FileHandler(loggingPath.toString(), 10000, 5, true);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException | SecurityException e){
            System.err.println(e.getMessage());
        }

        //actual start of application
        Plant pilea = PlantFactory.createPlant("Pilea", Plant.Categories.GREENPLANT);
        Plant cactus = PlantFactory.createPlant("Cacta", Plant.Categories.SUCCULENT);

        System.out.println(pilea.readSensor("Hygrometer"));

        WateringHandler wateringHandler = new WateringHandler();
        TemperatureHandler temperatureHandler = new TemperatureHandler();
        LightHandler lightHandler = new LightHandler();
        
        wateringHandler.setNextStep(temperatureHandler);
        temperatureHandler.setNextStep(lightHandler);
        wateringHandler.careForPlant(pilea);

        Collection<Plant> collection = new Collection<>("gioanna giorgia");
        collection.addElement(pilea);
        collection.addElement(cactus);
        //collection.displayElements();
        collection.removeElement(pilea);
        collection.removeElement(pilea);
        //collection.displayElements();
    }
}