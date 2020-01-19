package game.levels;

import collidable.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * LevelFactory information interface.
 *
 * @author Tomer Ovadia
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return paddle width
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return The Blocks that make up each level, each block contains its
     * size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
