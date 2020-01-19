package factories;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;

/**
 * The type Block image drawer.
 *
 * @author Tomer Ovadia.
 */
public class BlockImageDrawer implements BlockDrawer {
    private Image image;

    /**
     * Instantiates a new Block image drawer.
     *
     * @param im Image
     */
    public BlockImageDrawer(Image im) {
        this.image = im;
    }

    /**
     * Draws block according to specified fill information.
     *
     * @param d draw surface
     * @param rec Rectangle to draw
     */
    public void draw(DrawSurface d, Rectangle rec) {
        int x = (int) rec.getUpperLeft().getX();
        int y = (int) rec.getUpperLeft().getY();
        d.drawImage(x, y, this.image);
    }
}