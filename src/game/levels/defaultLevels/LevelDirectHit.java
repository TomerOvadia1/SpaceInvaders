package game.levels.defaultLevels;

import collidable.Block;
import constants.LevelsConsts;
import game.levels.VelocityConstructor;
import game.levels.defaultLevels.backgroundgraphics.DirectHitBackground;
import game.levels.LevelInformation;
import geometry.Point;
import sprites.Sprite;
import sprites.Velocity;
import constants.Consts;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelDirectHit Class.
 *
 * @author Tomer Ovadia
 */
public class LevelDirectHit implements LevelInformation {

    /**
     * @return number of balls for this level
     */
    public int numberOfBalls() {
        return LevelsConsts.DIRECT_HIT_BALL_NUM;
    }

    /**
     * The initial velocity of each ball.
     * @return a list with initial ball velocities
     */
    public List<Velocity> initialBallVelocities() {
        //shared algorithm for initializing balls velocity
        VelocityConstructor vc = new VelocityConstructor(this.numberOfBalls());
        return vc.create();
    }

    /**
     * @return paddle's speed
     */
    public int paddleSpeed() {
        return Consts.PADDLE_DEFAULT_SPEED;
    }

    /**
     * @return paddle's width
     */
    public int paddleWidth() {
        return Consts.PADDLE_DEFAULT_WIDTH;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new DirectHitBackground();

    }

    /**
     * @return The Blocks that make up each level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int x = (int) LevelsConsts.DIRECT_HIT_BACKGROUND_TARGET_POINT.getX()
                - LevelsConsts.DIRECT_HIT_BLOCK_WIDTH / 2;
        int y = (int) LevelsConsts.DIRECT_HIT_BACKGROUND_TARGET_POINT.getY()
                - LevelsConsts.DIRECT_HIT_BLOCK_HEIGHT / 2;
        Block b = new Block(new Point(x, y),
                LevelsConsts.DIRECT_HIT_BLOCK_WIDTH,
                LevelsConsts.DIRECT_HIT_BLOCK_HEIGHT, Color.RED);
        b.setHitCount(1);
        blockList.add(b);
        return blockList;
    }

    /**
     * @return Number of blocks that should be removed before the level is
     * considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
