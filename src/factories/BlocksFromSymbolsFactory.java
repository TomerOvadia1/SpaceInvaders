
package factories;

import collidable.Block;

import java.util.Map;

/**
 * The type Blocks from symbols factory.
 *
 * @author Tomer Ovadia.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidth  the spacer width
     * @param blockCreator the block creator
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidth,
                                    Map<String, BlockCreator> blockCreator) {
        this.spacerWidths = spacerWidth;
        this.blockCreators = blockCreator;
    }

    /**
     * Is space symbol boolean.
     *
     * @param s space symbol
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s block symbol
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);

    }

    /**
     * Gets block.
     *
     * @param s    block symbol
     * @param xpos x position
     * @param ypos y position
     * @return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Get space width int.
     *
     * @param s space symbol
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}