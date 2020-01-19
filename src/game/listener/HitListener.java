package game.listener;

import collidable.Block;
import sprites.Ball;

/**
 * HitListener Interface.
 *
 * @author Tomer Ovadia.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
