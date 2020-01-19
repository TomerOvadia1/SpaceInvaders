package game.listener;

import collidable.Block;
import sprites.Ball;

/**
 * PrintingHitListener class.
 *
 * @author Tomer Ovadia.
 */
public class PrintingHitListener implements HitListener {
    /**
     * announce on hit event.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitCount()
                + " points was hit.");
    }
}
