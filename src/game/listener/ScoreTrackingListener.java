package game.listener;

import collidable.Block;
import sprites.Ball;

/**
 * ScoreTrackingListener Class.
 *
 * @author Tomer Ovadia
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter for a score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * announce on hit event.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitCount() == 0) {
            this.currentScore.increase(100);
        }

    }

}
