package game.levels.defaultLevels;

import collidable.Block;
import constants.Consts;
import constants.LevelsConsts;
import game.levels.VelocityConstructor;
import game.levels.defaultLevels.backgroundgraphics.GreenThreeBackground;
import game.levels.LevelInformation;
import geometry.Point;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelGreenThree Class.
 *
 * @author Tomer Ovadia
 */
public class LevelGreenThree implements LevelInformation {

    /**
     * @return number of balls for this level
     */
    public int numberOfBalls() {
        return LevelsConsts.GREEN3_BALL_NUM;
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
        return "Green 3";
    }

    /**
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new GreenThreeBackground();

    }

    /**
     * @return The Blocks that make up each level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        //blocks starting position
        int x = LevelsConsts.GREEN3_BLOCK1_X;
        int y = LevelsConsts.GREEN3_BLOCK1_Y;
        //each row has a color
        java.awt.Color[] rowColor = {Color.GRAY, Color.RED, Color.YELLOW,
                Color.BLUE, Color.PINK, Color.GREEN};
        //number of blocks per row
        int blocksNum = LevelsConsts.GREEN3_ROW1_SIZE;
        //first point of each block;
        Point[] pointsRow = new Point[LevelsConsts.GREEN3_ROW1_SIZE];
        //blocks array
        List<Block> blocks = new ArrayList<>();
        //block creation
        for (int i = 0; i < LevelsConsts.GREEN3_BLOCK_ROWS; i++) {
            for (int j = 0; j < blocksNum; j++) {
                pointsRow[i] = new Point(x, y);
                Block b = new Block(pointsRow[i],
                        LevelsConsts.GREEN3_BLOCK_WIDTH,
                        LevelsConsts.GREEN3_BLOCK_HEIGHT, rowColor[i]);
                blocks.add(b);
                if (i == 0) {
                    b.setHitCount(LevelsConsts.GREEN3_BLOCK_HIT_TOP_ROW);
                } else {
                    b.setHitCount(LevelsConsts.GREEN3_BLOCK_HIT_NUMBER);
                }
                //step back in position to create another block
                x -= LevelsConsts.GREEN3_BLOCK_WIDTH;
            }
            blocksNum--;
            x = LevelsConsts.GREEN3_BLOCK1_X;
            y += LevelsConsts.GREEN3_BLOCK_HEIGHT;
        }
        return blocks;
    }

    /**
     * @return Number of blocks that should be removed before the level is
     * considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
