package game.levels;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import sprites.Sprite;

import java.awt.Color;

/**
 * Color background class.
 *
 * @author Tomer Ovadia.
 */
public class ColorBackground implements Sprite {
    private Color color;

    /**
     * Instantiates a new Color background.
     *
     * @param c the color
     */
    public ColorBackground(Color c) {
        this.color = c;
    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt delta time
     */
    public void timePassed(double dt) {

    }

    /**
     * Add a sprite object to the game class.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    public void addToGame(SpaceInvaderLevel g) {
        g.addSprite(this);
    }
}
