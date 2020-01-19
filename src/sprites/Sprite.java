package sprites;
import biuoop.DrawSurface;
import game.SpaceInvaderLevel;

/**
 * Sprite Interface.
 *
 * @author Tomer Ovadia
 */
public interface Sprite {
    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    void timePassed(double dt);

    /**
     * Add a sprite object to the game class.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    void addToGame(SpaceInvaderLevel g);
}