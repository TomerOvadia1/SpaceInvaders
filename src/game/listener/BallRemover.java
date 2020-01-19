package game.listener;

import collidable.Block;
import game.SpaceInvaderLevel;
import sprites.Ball;

/**
 * BallRemover Class.
 *
 * @author Tomer Ovadia
 */
public class BallRemover implements HitListener {
    private SpaceInvaderLevel spaceInvaderLevel;
    private Counter removedBalls;

    /**
     * Constructor.
     *
     * @param g game level
     */
    public BallRemover(SpaceInvaderLevel g) {
        this.spaceInvaderLevel = g;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove hitter ball from spaceInvaderLevel
        hitter.removeFromGame(this.spaceInvaderLevel);
        this.spaceInvaderLevel.getPlayerShots().remove(hitter);
        this.spaceInvaderLevel.getAlienShots().remove(hitter);
        //decrease ball counter
        ////this.spaceInvaderLevel.getBallCounter().decrease(1);
        //increase removed balls counter
        ////this.removedBalls.increase(1);
    }
}
