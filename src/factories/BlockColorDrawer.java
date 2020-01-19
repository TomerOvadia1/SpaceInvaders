package factories;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block color drawer.
 *
 * @author Tomer Ovadia.
 */
public class BlockColorDrawer implements BlockDrawer {
    private Color color;

    /**
     * Instantiates a new Block color drawer.
     *
     * @param c the color
     */
    public BlockColorDrawer(Color c) {
        this.color = c;
    }

    /**
     * Draws block according to specified fill information.
     *
     * @param d draw surface
     * @param rec Rectangle to draw
     */
    public void draw(DrawSurface d, Rectangle rec) {
        //get coordinates
        int x = (int) rec.getUpperLeft().getX();
        int y = (int) rec.getUpperLeft().getY();
        int width = (int) rec.getWidth();
        int height = (int) rec.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }
}
