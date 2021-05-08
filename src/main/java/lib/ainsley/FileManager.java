package lib.ainsley;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileManager {


    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     * Method to load an image from resources folder
     * @param path
     * @return
     */
    public static Image loadImage(String path) {
        URL url = ClassLoader.getSystemResource(path);
        return toolkit.getImage(url);
    }

    /**
     * Reads a JSON file
     * @param path receives the path to the file
     * @return a string with the content of the file
     * @throws IOException signals that an I/O exception of some sort has occurred
     */
    public static String readJSONfile(String path) throws IOException {
        // Read file into a string
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

}
