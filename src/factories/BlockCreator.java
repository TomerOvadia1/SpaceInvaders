package factories;

import collidable.Block;

/**
 * The interface Block creator.
 *
 * @author Tomer Ovadia.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos x position
     * @param ypos y position
     * @return new block with specified coordinates
     */
    Block create(int xpos, int ypos);
}
