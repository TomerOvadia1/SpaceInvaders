package factories;

import collidable.Block;

import java.awt.Color;
import java.util.List;

/**
 * The type Block factory.
 *
 * @author Tomer Ovadia.
 */
public class BlockFactory implements BlockCreator {
    private static final int DEFAULT_HEIGHT = 1;
    private static final int DEFAULT_WIDTH = 1;
    private int height;
    private int width;
    //private int xPos;
    //private int yPos;
    private int hitPoints;
    private List<BlockDrawer> blockDrawers;
    private Color stroke = null;

    /**
     * Instantiates a new Block factory.
     */
    public BlockFactory() {
    }

    /**
     * Create a block at the specified location.
     *
     * @param xpos x position
     * @param ypos y position
     * @return new block with specified coordinates
     */
    public Block create(int xpos, int ypos) {
        Block b = new Block(xpos, ypos);
        b.setHeight(this.height);
        b.setWidth(this.width);
        b.setHitCount(this.hitPoints);
        b.setStroke(this.stroke);
        b.setBlockDrawers(this.blockDrawers);
        return b;
    }

    /**
     * Set height.
     *
     * @param blockHeight the block height
     */
    public void setHeight(int blockHeight) {
        this.height = blockHeight;
    }

    /**
     * Set width.
     *
     * @param blockWidth the block width
     */
    public void setWidth(int blockWidth) {
        this.width = blockWidth;
    }

    /**
     * Set hit points.
     *
     * @param points the points
     */
    public void setHitPoints(int points) {
        this.hitPoints = points;
    }

    /**
     * Set block drawers.
     *
     * @param blockDrawerList the block drawer list
     */
    public void setBlockDrawers(List<BlockDrawer> blockDrawerList) {
        this.blockDrawers = blockDrawerList;
    }

    /**
     * Set stroke.
     *
     * @param color the color
     */
    public void setStroke(Color color) {
        this.stroke = color;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets block drawers.
     *
     * @return the block drawers
     */
    public List<BlockDrawer> getBlockDrawers() {
        return blockDrawers;
    }
}
