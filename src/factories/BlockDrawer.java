package factories;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * The interface Block drawer.
 *
 * @author Tomer Ovadia
 */
public interface BlockDrawer {
    /**
     * Draws block according to specified fill information.
     *
     * @param d   the draw surface
     * @param rec rectangle base for a block
     */
    void draw(DrawSurface d, Rectangle rec);
}
