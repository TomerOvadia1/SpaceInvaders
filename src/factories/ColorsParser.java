package factories;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * The type Colors parser.
 *
 * @author Tomer Ovadia.
 */
public class ColorsParser {
    private static final String RGB_PRE = "RGB(";
    private static final String RGB_POST = ")";

    /**
     * parse color definition and return the specified color.
     *
     * @param s the string
     * @return color color
     */
    public Color colorFromString(String s) {

        Color color;
        //parse color from RGB stats
        if (s.startsWith(RGB_PRE) && s.endsWith(RGB_POST)) {
            s = s.substring(RGB_PRE.length(),
                    s.length() - RGB_POST.length());
            String[] rgb = s.split(",");
            // not in the form of - new Color(r,g,b)
            if (rgb.length != 3) {
                throw new RuntimeException("Error: Color RGB wrong usage.");
            }
            try {
                int red = Integer.parseInt(rgb[0]);
                int green = Integer.parseInt(rgb[1]);
                int blue = Integer.parseInt(rgb[2]);
                return new Color(red, green, blue);
                // r,g,b are out of range or aren't integers
            } catch (Exception e) {
                throw new RuntimeException("Error: Color RGB wrong usage.");
            }
        } else {
            //since Color.getColor uses system properties , it should be
            //avoided , so i use the following code:(invoke a field)
            try {
                Field field = Color.class.getField(s);
                color = (Color) field.get(null);
                return color;
            } catch (Exception e) {
                throw new RuntimeException("Error: Unknown color.");

            }
        }

    }
}
