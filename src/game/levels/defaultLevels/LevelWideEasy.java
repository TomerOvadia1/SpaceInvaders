package game.levels.defaultLevels;

import collidable.Block;
import constants.LevelsConsts;
import game.levels.VelocityConstructor;
import game.levels.defaultLevels.backgroundgraphics.WideEasyBackground;
import game.levels.LevelInformation;
import geometry.Point;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelWideEasy Class.
 *
 * @author Tomer Ovadia.
 */
public class LevelWideEasy implements LevelInformation {

    /**
     * @return number of balls for this level
     */
    public int numberOfBalls() {
        return LevelsConsts.WIDE_EASY_BALL_NUM;
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
        return LevelsConsts.WIDE_EASY_PADDLE_SPEED;
    }

    /**
     * @return paddle's width
     */
    public int paddleWidth() {
        return LevelsConsts.WIDE_EASY_PADDLE_WIDTH;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new WideEasyBackground();

    }

    /**
     * @return The Blocks that make up each level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int x = (int) LevelsConsts.WIDE_EASY_BLOCK_START_POINT.getX();
        int y = (int) LevelsConsts.WIDE_EASY_BLOCK_START_POINT.getY();
        Color[] colors = {Color.CYAN, Color.PINK, Color.BLUE, Color.GREEN,
                Color.YELLOW, Color.ORANGE, Color.RED};
        int colorIndex = 0;
        for (int i = 0; i < LevelsConsts.WIDE_EASY_BLOCK_NUM; i++) {
            if (i > 0 && i % 2 == 0) {
                colorIndex++;
            }
            //check for index out of bounds
            if (colorIndex > colors.length - 1) {
                colorIndex = colors.length - 1;
            }
            Block b = new Block(new Point(x, y),
                    LevelsConsts.WIDE_EASY_BLOCK_WIDTH,
                    LevelsConsts.WIDE_EASY_BLOCK_HEIGHT, colors[colorIndex]);
            b.setHitCount(1);
            blockList.add(b);
            x -= LevelsConsts.WIDE_EASY_BLOCK_WIDTH;
        }
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
