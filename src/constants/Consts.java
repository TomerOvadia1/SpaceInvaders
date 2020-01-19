package constants;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Constants class.
 *
 * @author Tomer Ovadia
 */
public final class Consts {
    //----------------------GUI DEFAULT SETTINGS-------------------------------
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    //--------------------------------GRAPHICS---------------------------------
    //INFO BLOCK
    public static final int INFO_BLOCK_HEIGHT = 20;
    public static final int INFO_BLOCK_WIDTH = GUI_WIDTH;
    public static final Point INFO_BLOCK_POINT =
            new Point(0, 0);
    public static final Color INFO_BLOCK_COLOR = Color.lightGray;
    public static final Rectangle INFO_REC =
            new Rectangle(Consts.INFO_BLOCK_POINT, Consts.INFO_BLOCK_WIDTH,
                    Consts.INFO_BLOCK_HEIGHT);
    public static final int INFO_BLOCK_TEXT_SPACE = INFO_BLOCK_WIDTH / 3;

    //SCREEN BOUNDS
    public static final int TOP_BOUND_WIDTH = GUI_WIDTH;
    public static final int TOP_BOUND_HEIGHT = 25;
    public static final int SIDE_BOUND_WIDTH = 25;
    public static final int SIDE_BOUND_HEIGHT = GUI_HEIGHT;
    public static final int BOT_BOUND_WIDTH =
            TOP_BOUND_WIDTH - (2 * SIDE_BOUND_WIDTH);
    public static final Point TOP_BOUND_POINT =
            new Point(0, INFO_BLOCK_POINT.getY() + INFO_BLOCK_HEIGHT);
    public static final Point POINT_LEFT_BOUND =
            new Point(TOP_BOUND_POINT.getX() - SIDE_BOUND_WIDTH,
                    TOP_BOUND_POINT.getY() + TOP_BOUND_HEIGHT);
    public static final Point BOT_BOUND_POINT =
            new Point(SIDE_BOUND_WIDTH, GUI_HEIGHT);
    public static final Point RIGHT_BOUND_POINT =
            new Point(GUI_WIDTH - SIDE_BOUND_WIDTH + SIDE_BOUND_WIDTH,
                    TOP_BOUND_POINT.getY() + TOP_BOUND_HEIGHT);

    //SCORE INDICATOR
    public static final Point SCORE_INDICATOR_POINT =
            new Point((INFO_BLOCK_WIDTH / 2) - 30, INFO_BLOCK_HEIGHT / 2);

    //LIVES INDICATOR
    public static final Point LIVES_INDICATOR_POINT =
            new Point(SCORE_INDICATOR_POINT.getX() - INFO_BLOCK_TEXT_SPACE,
                    SCORE_INDICATOR_POINT.getY());

    //LEVEL NAME INDICATOR
    public static final String LEVEL_NAME = "LEVEL NAME: ";
    public static final Point LEVEL_NAME_INDICATOR_POINT =
            new Point((GUI_WIDTH / 3) * 2, LIVES_INDICATOR_POINT.getY());

    public static final int LIVES_INDICATOR_RADIUS =
            Consts.INFO_BLOCK_HEIGHT / 5;

    //HIGHSCORE TABLE
    public static final int HIGHSCORE_TABLE_SIZE = 5;
    public static final int TABLE_COL_SPACE = GUI_WIDTH / 4;
    public static final int TABLE_LINE_SPACE = GUI_WIDTH / 20;
    public static final int TABLE_FONT_SIZE = 25;
    //---------------------Animation related constants-------------------------
    public static final int ANIMATION_SLEEP = 50;
    public static final int ANIMATION_FPS = 60;
    //COUNT DOWN
    public static final double COUNT_DOWN_SEC = 2;
    public static final int COUNT_DOWN_FROM = 3;
    public static final int COUNT_DOWN_FONT_SIZE = 42;
    //PAUSE SCREEN
    public static final int PAUSE_SCREEN_CIRC_RADIUS = GUI_WIDTH / 4;
    public static final int PAUSE_SCREEN_REC_WIDTH = GUI_WIDTH / 10;
    public static final int PAUSE_SCREEN_REC_HEIGHT = GUI_HEIGHT / 3;
    //END SCREEN
    public static final String END_SCREEN_WIN_MSG = "YOU WIN !";
    public static final String END_SCREEN_GAME_OVER = "GAME OVER.";
    public static final int END_SCREEN_MSG_FONT_SIZE = 50;
    public static final int END_SCREEN_SCORE_FONT_SIZE = 30;
    public static final String END_SCREEN_SCORE_MSG = "YOUR SCORE IS: ";
    public static final String END_SCREEN_PRESS_SPACE_MSG =
            "PRESS SPACE TO CONTINUE";

    //----------------------------BALL AND PADDLE------------------------------

    //BLOCKS
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HIT_FONT_SIZE = 13;
    //PADDLE
    public static final int PADDLE_DEFAULT_WIDTH = 70;
    public static final int PADDLE_DEFAULT_HEIGHT = 20;
    public static final Point PADDLE_DEFAULT_POINT =
            new Point((GUI_WIDTH - PADDLE_DEFAULT_WIDTH) / 2,
                    GUI_HEIGHT - TOP_BOUND_HEIGHT - PADDLE_DEFAULT_HEIGHT);
    public static final int PADDLE_DEFAULT_SPEED = 455;
    public static final int PADDLE_REG_NUM = 5;
    public static final int PADDLE_REG1_ANGLE = 300;
    public static final int PADDLE_REG_ANGLE_DIFF = 30;

    //BALL
    //DEAFULT
    public static final Point BALL_DEFAULT_CENTER =
            new Point(PADDLE_DEFAULT_POINT.getX()
                    + PADDLE_DEFAULT_WIDTH / 2, PADDLE_DEFAULT_POINT.getY()
                    - PADDLE_DEFAULT_HEIGHT / 2);
    public static final int BALL_DEFAULT_RADIUS = 3;
    public static final Color BALL1_DEFAULT_COLOR =
            new Color(255, 51, 51);
    //new Color(51,153,255);
    public static final int BALL_DEFAULT_SPEED = 4;

    //-----------------------------GAME LOGIC----------------------------------

    //SCORE
    public static final int SCORE_ADD_BLOCK_HIT = 5;
    public static final int SCORE_ADD_BLOCK_REMOVED = 10;
    public static final int SCORE_ADD_REMOVED_ALL = 100;
    public static final String SCORE_TEXT = "SCORE: ";
    //LIVES
    public static final int LIVES_NUM = 3;
    public static final String LIVES_TEXT = "Lives: ";


}