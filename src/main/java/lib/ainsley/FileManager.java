package lib.ainsley;

import java.awt.*;
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

}
