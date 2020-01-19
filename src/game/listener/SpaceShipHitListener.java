package game.listener;

import collidable.Block;
import game.SpaceInvaderLevel;
import sprites.Ball;

/**
 * The type Space ship hit listener.
 */
public class SpaceShipHitListener implements HitListener {
    private SpaceInvaderLevel spaceInvaderLevel;

    /**
     * Constructor.
     *
     * @param spaceInvaderLevel current game level
     */
    public SpaceShipHitListener(SpaceInvaderLevel spaceInvaderLevel) {
        this.spaceInvaderLevel = spaceInvaderLevel;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the spaceInvaderLevel. Function removes this listener from
     * removed block.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.spaceInvaderLevel.getSpaceShip().setAsBeenHit();
    }
}
