package smart_plant_app.main_objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Collection<T extends House> {

    private final Path path = Paths.get("smartplantapp", "src", "main", "resources", "collection.txt");
    private final String stringPath = path.toString();

    /**
     * Add a plant or a location to the collection
     *
     * @param element element to add
     */
    public void addElement(T element) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(stringPath, true))) {
            writer.write(element.showDetails());
            writer.newLine();
            System.out.println("Element written to file: " + path);
        } catch (java.io.IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Remove a plant or a location to the collection
     *
     * @param element element to remove
     */
    public void removeElement(T element) {
    }

    /*
     * Print every line of the collection
     */
    public void displayElements() {
        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String line="";
            while ((line = reader.readLine())!=null){
                System.out.println(line);
            }
        } catch (java.io.IOException e) {
            System.err.println("Error while opening the file" + e.getMessage());
        }
    }
}
