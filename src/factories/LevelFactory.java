package factories;

import collidable.Block;
import game.levels.LevelInformation;
import sprites.Sprite;
import sprites.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level factory.
 *
 * @author Tomer Ovadia.
 */
public class LevelFactory implements LevelInformation {
    private List<Block> blocks;
    private int numOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite background;
    private String levelName;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Level factory.
     */
    public LevelFactory() {
        blocks = new ArrayList<>();
        numOfBalls = 0;
        velocities = new ArrayList<>();
        paddleSpeed = 0;
        paddleWidth = 0;
        background = null;
        levelName = "";
        numberOfBlocksToRemove = 0;
    }

    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * @return The initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return The Blocks that make up each level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Add block.
     *
     * @param b the b
     */
    public void addBlock(Block b) {
        this.blocks.add(b);
    }

    /**
     * @return Number of blocks that should be removed before the level is
     * considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Sets background.
     *
     * @param bg the background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }

    /**
     * Sets blocks.
     *
     * @param b the blocks
     */
    public void setBlocks(List<Block> b) {
        this.blocks = b;
    }

    /**
     * Sets level name.
     *
     * @param name the level name
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * Sets num of balls.
     *
     * @param num the num of balls
     */
    public void setNumOfBalls(int num) {
        this.numOfBalls = num;
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param num the number of blocks to remove
     */
    public void setNumberOfBlocksToRemove(int num) {
        this.numberOfBlocksToRemove = num;
    }

    /**
     * Sets paddle speed.
     *
     * @param speed the paddle speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Sets paddle width.
     *
     * @param width the paddle width
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * Sets velocities.
     *
     * @param velo the velocities
     */
    public void setVelocities(List<Velocity> velo) {
        this.velocities = velo;
    }
}