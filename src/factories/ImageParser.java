package factories;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Image parser.
 *
 * @author Tomer Ovadia.
 */
public class ImageParser {

    /**
     * Image from string image.
     *
     * @param s the string
     * @return the image
     */
    public static Image imageFromString(String s) {
        Image image;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            image = ImageIO.read(is);
            is.close();
            return image;
        } catch (IOException ex) {
            throw new RuntimeException("Fill Error: illegal "
                    + "Image path");
        }
    }
}
