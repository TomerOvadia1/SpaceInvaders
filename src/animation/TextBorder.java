package animation;

import biuoop.DrawSurface;

/**
 * TextBorder class.
 *
 * @author Tomer Ovadia
 */
public class TextBorder {
    /**
     * function draws a frame for text.
     *
     * @param d    draw surface
     * @param x    position
     * @param y    position
     * @param text to draw
     * @param size font size
     */
    public void textEffect(DrawSurface d, int x, int y, String text,
                           int size) {
        d.drawText(x - 1, y, text, size);
        d.drawText(x + 1, y, text, size);
        d.drawText(x, y + 1, text, size);
        d.drawText(x, y - 1, text, size);
    }
}
