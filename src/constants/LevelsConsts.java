package constants;

import geometry.Point;
import java.awt.Color;
import static constants.Consts.GUI_HEIGHT;
import static constants.Consts.GUI_WIDTH;
import static constants.Consts.TOP_BOUND_HEIGHT;
import static constants.Consts.POINT_LEFT_BOUND;
import static constants.Consts.SIDE_BOUND_WIDTH;

/**
 * Levels Consts class.
 *
 * @author Tomer Ovadia
 */
public class LevelsConsts {
    //LEVEL
    public static final int BASE_VELOCITY_ANGLE = 0;
    //LEVEL 1/ LEVEL DIRECT HIT
    public static final int DIRECT_HIT_BALL_NUM = 1;
    //background
    public static final Point DIRECT_HIT_BACKGROUND_TARGET_POINT =
            new Point(GUI_WIDTH / 2, GUI_HEIGHT / 4);
    //blocks
    public static final int DIRECT_HIT_BLOCK_WIDTH = 25;
    public static final int DIRECT_HIT_BLOCK_HEIGHT = 25;

    //------------------------------------------------------------------------
    //LEVEL 2/ LEVEL WIDE EASY
    public static final int WIDE_EASY_BALL_NUM = 10;
    public static final int WIDE_EASY_PADDLE_SPEED = 3;
    //background
    public static final Point WIDE_EASY_BACKGROUND_SUN_CENTER =
            new Point(GUI_WIDTH / 4, GUI_HEIGHT / 4);
    public static final int WIDE_EASY_BACKGROUND_SUN_LINES = 150;
    //blocks
    public static final int WIDE_EASY_BLOCK_WIDTH = 50;
    public static final int WIDE_EASY_BLOCK_HEIGHT = 25;
    public static final Point WIDE_EASY_BLOCK_START_POINT =
            new Point(Consts.RIGHT_BOUND_POINT.getX()
                    - WIDE_EASY_BLOCK_WIDTH, (GUI_HEIGHT - TOP_BOUND_HEIGHT
                    - Consts.INFO_BLOCK_HEIGHT) / 2);
    public static final int WIDE_EASY_BLOCK_NUM =
            (GUI_WIDTH - Consts.SIDE_BOUND_WIDTH * 2) / WIDE_EASY_BLOCK_WIDTH;
    public static final int WIDE_EASY_PADDLE_WIDTH = 600;

    //------------------------------------------------------------------------
    //LEVEL 3/ LEVEL GREEN 3
    public static final int GREEN3_BALL_NUM = 2;
    //background
    public static final Point GREEN3_BACKGROUND_BUILDING_POINT =
            new Point(POINT_LEFT_BOUND.getX() + SIDE_BOUND_WIDTH * 3,
                    (GUI_HEIGHT / 4) * 3);
    public static final Color GREEN3_BACKGROUND_BUILDING_COLOR =
            new Color(51, 51, 51);
    public static final int GREEN3_BACKGROUND_BUILDING_WIDTH = GUI_WIDTH / 10;
    public static final int GREEN3_BACKGROUND_BUILDING_HEIGHT =
            GUI_HEIGHT / 4;
    public static final int GREEN3_BACKGROUND_WINDOWS = 6;
    public static final int GREEN3_BACKGROUND_WINDOWS_HEIGHT = 20;
    public static final int GREEN3_BACKGROUND_WINDOWS_WIDTH = 10;
    public static final int GREEN3_BACKGROUND_LIGHT_RADIUS = 10;
    //blocks
    public static final int GREEN3_BLOCK_WIDTH = 50;
    public static final int GREEN3_BLOCK_HEIGHT = 25;
    //BLOCKS
    public static final int GREEN3_ROW1_SIZE = 10;
    public static final int GREEN3_BLOCK_ROWS = 5;
    public static final int GREEN3_BLOCK_HIT_TOP_ROW = 2;
    public static final int GREEN3_BLOCK_HIT_NUMBER = 1;
    public static final int GREEN3_BLOCK1_X =
            GUI_WIDTH - Consts.SIDE_BOUND_WIDTH - Consts.BLOCK_WIDTH;
    public static final int GREEN3_BLOCK1_Y = 100;

    //------------------------------------------------------------------------
    //LEVEL 4/ LEVEL FINAL FOUR
    public static final int FINAL4_BALL_NUM = 3;
    //background
    public static final Color FINAL4_BACKGROUND_COLOR =
            new Color(51, 153, 255);
    public static final Point FINAL4_BACKGROUND_CLOUD =
            new Point(GUI_WIDTH / 6, GUI_HEIGHT - GUI_HEIGHT / 3);
    public static final int FINAL4_BACKGROUND_LINE_LEN = GUI_HEIGHT / 3;
    public static final int FINAL4_BACKGROUND_LINE_NUM = 12;
    //BLOCKS
    public static final int FINAL4_BLOCK_HIT_TOP_ROW = 2;
    public static final int FINAL4_BLOCK_HIT_NUMBER = 1;
    public static final int FINAL4_BLOCK1_X =
            GUI_WIDTH - Consts.SIDE_BOUND_WIDTH - Consts.BLOCK_WIDTH;
    public static final int FINAL4_BLOCK1_Y = 100;
    public static final int FINAL4_BLOCK_WIDTH = 50;
    public static final int FINAL4_BLOCK_HEIGHT = 25;
    public static final int FINAL4_ROW1_SIZE =
            (GUI_WIDTH - Consts.SIDE_BOUND_WIDTH * 2) / FINAL4_BLOCK_WIDTH;
    public static final int FINAL4_BLOCK_ROWS = 7;
}
