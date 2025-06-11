package smart_plant_app.misc;

public class Misc {

    /**
     * Method to sanitize string.
     * Provides basic sanitization leaving only allowed characters and removing spaces.
     * If spaces between words are present, they are replaced with "_".
     * 
     * @param string String to sanitize
     * @return the sanitized String
     */
    public static String sanitize (String string){
        String sanitizedString = string
                        .replaceAll("[\\\\/:*?\"<>|]", "") //remove illegal characters
                        .trim() //remove spaces at end & beginning
                        .replaceAll(" ", "_"); //replace internal spaces with underscore
        return sanitizedString;
    }
}
