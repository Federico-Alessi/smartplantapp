package smart_plant_app.main_objects;

// This interface represents a House in the smart plant application.
// It defines the basic structure that any class implementing it must follow.
public interface House {
    // Method to display the details of the house.
    public String showDetails();

    // Method to get the name of the house.
    public String getName();

    //Method to print the result of showDetails()
    public void printDetails();
}
