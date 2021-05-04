package lib.ainsley;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileManager {

    /**
     * Method to load an image from resources folder
     * @param path
     * @return
     */
    public static Image loadImage(String path) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL url = ClassLoader.getSystemResource(path);
        return toolkit.getImage(url);
    }

    public static String readFileToJSON(String path) throws IOException {
        // Read file into a string
        BufferedReader reader = new BufferedReader(new FileReader("data.json"));
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
