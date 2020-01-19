package game.levels;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import sprites.Sprite;

import java.awt.Image;

/**
 * The type Image background.
 *
 * @author Tomer Ovadia.
 */
public class ImageBackground implements Sprite {
    private Image image;

    /**
     * Instantiates a new Image background.
     *
     * @param im the image
     */
    public ImageBackground(Image im) {
        this.image = im;
    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
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
