package smart_plant_app.main_objects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Collection<T extends House> {
    private static final Logger logger = Logger.getLogger("globalLogger");
    private final String filename;
    private final Path path;

    /**
     * Constructor for the Collection
     * 
     * @param filename Name of the file where the collection will be stored
     */
    public Collection(String filename){
        this.filename = filename
                    .replaceAll("[\\\\/:*?\"<>|]", "") //remove illegal characters
                    .trim() //remove spaces at end & beginning
                    .replaceAll(" ", "_") //replace internal spaces with underscore
                    + ".txt"; //add format
        this.path = Paths.get("smartplantapp", "src", "main", "resources", this.filename);
    }

    /**
     * Add a plant or a location to the collection
     *
     * @param element element to add
     */
    public void addElement(T element) {

        try {
            // Append the element's details as a new line to the file using NIO
            Files.write(
                    path,
                    Collections.singletonList(element.showDetails()),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE
            );
            System.out.println(element.getName() + " added to your collection");
            logger.log(Level.INFO, "{0} added correctly", element.getName());
        } catch (IOException e) {
            logger.log(Level.WARNING, "exception while adding {0}: {1}", new Object[]{element.getName(), e.getMessage()});
        }
    }

    /**
     * Remove a plant or a location to the collection
     *
     * @param element element to remove
     */
    public void removeElement(T element) {
        List<String> collectionCopy = new ArrayList<>();

        //copy the collection in the list
        try {
            Files.lines(path).forEach(line-> {
                collectionCopy.add(line);
            });
        } catch (java.io.IOException e) {
            logger.log(Level.WARNING, "exception while removing {0}: {1}", new Object[]{element.getName(), e.getMessage()});
            return;
        }
        
        // Delete te existing file and copy list to a new one
        int index = collectionCopy.indexOf(element.showDetails());
        if (index!=-1) {
            collectionCopy.remove(index);
            System.out.println(element.getName() + " removed successfully.");
            logger.log(Level.INFO, "{0} removed successfully", element.getName());
            try{
                Files.delete(path);
                for (String item : collectionCopy){
                    Files.write(
                        path,
                        Collections.singletonList(item),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE
                    );
                }
            }catch (IOException e){
               System.err.println("Error while accessing the collection " + e.getMessage());
               logger.log(Level.WARNING, "exception while accessing the collection: {0}", e.getMessage());
           }
        } else {
            logger.log(Level.INFO, "{0} is not in your list", element.getName());
        }
    }

    /**
     * Display all the elements in the collection
     */
    public void displayElements() {
        try {
            Files.lines(path).forEach(line-> {
                System.out.println(line);
            });
        } catch (IOException e) {
            logger.log(Level.WARNING, "exception while accessing the collection: {0}", e.getMessage());
        }
    }
}
