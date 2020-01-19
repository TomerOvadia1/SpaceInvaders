package game.listener;

import collidable.Block;
import game.SpaceInvaderLevel;
import sprites.Ball;

/**
 * BlockRemover class.
 * a BlockRemover is in charge of removing blocks from the spaceInvaderLevel,
 * as well as keeping count of the number of blocks that was deleted.
 *
 * @author Tomer Ovadia
 */
public class BlockRemover implements HitListener {
    private SpaceInvaderLevel spaceInvaderLevel;

    /**
     * Constructor.
     *
     * @param spaceInvaderLevel current game level
     */
    public BlockRemover(SpaceInvaderLevel spaceInvaderLevel) {
        this.spaceInvaderLevel = spaceInvaderLevel;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the spaceInvaderLevel. Function removes this listener from removed
     * block.
     *
     * @param beingHit object that is hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //if hit count is greater than 0 decrease it to mark a hit
        if (beingHit.getHitCount() > 0) {
            beingHit.setHitCount(beingHit.getHitCount() - 1);
        }
        if (beingHit.getHitCount() == 0) {
            //remove block from spaceInvaderLevel
            beingHit.removeFromGame(this.spaceInvaderLevel);
            //remove this listener from the block
            beingHit.removeHitListener(this);
        }
    }
}