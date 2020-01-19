package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constants.Consts;
import game.listener.Counter;
import java.awt.Color;
import java.util.Random;

/**
 * EndScreen Class.
 *
 * @author Tomer Ovadia
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter livesCounter;
    private Counter scoreCounter;

    /**
     * Constructor.
     *
     * @param k     keyboard sensor
     * @param lives number of lives left
     * @param score players score
     */
    public EndScreen(KeyboardSensor k, Counter lives, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.livesCounter = lives;
        this.scoreCounter = score;
    }

    /**
     * run a one frame animation.
     *
     * @param d drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //get text position
        int upperX = (int) (d.getWidth() / 2);
        int upperY = (int) (d.getHeight() / 5);
        TextBorder tb = new TextBorder();
        upperX -= upperX / 3;
        String text;
        //player won
        if (this.livesCounter.getValue() > 0) {
            //draw background
            drawBackground(d, Color.WHITE);
            drawMultStars(d, 1, Color.WHITE, 100);
            text = Consts.END_SCREEN_WIN_MSG;
            //display massage
            d.setColor(Color.BLACK);
            tb.textEffect(d, upperX, upperY, text,
                    Consts.END_SCREEN_MSG_FONT_SIZE);
            d.setColor(Color.WHITE);
            d.drawText(upperX, upperY, text, Consts.END_SCREEN_MSG_FONT_SIZE);
        } else {
            //draw background
            drawBackground(d, Color.WHITE);
            drawMultStars(d, 1, Color.WHITE, 100);
            text = Consts.END_SCREEN_GAME_OVER;
            //display massage
            d.setColor(Color.BLACK);
            tb.textEffect(d, upperX - 20, upperY, text,
                    Consts.END_SCREEN_MSG_FONT_SIZE);
            d.setColor(Color.WHITE);
            d.drawText(upperX - 20, upperY, text,
                    Consts.END_SCREEN_MSG_FONT_SIZE);

        }
        //draw score
        int lowerY = (int) (d.getHeight() / 5) * 4;
        text = Consts.END_SCREEN_SCORE_MSG + this.scoreCounter.getValue();
        d.setColor(Color.BLACK);
        tb.textEffect(d, upperX, lowerY, text,
                Consts.END_SCREEN_SCORE_FONT_SIZE);
        d.setColor(Color.WHITE);
        d.drawText(upperX, lowerY, text, Consts.END_SCREEN_SCORE_FONT_SIZE);
        lowerY += lowerY / 10;
        text = Consts.END_SCREEN_PRESS_SPACE_MSG;
        d.setColor(Color.BLACK);
        tb.textEffect(d, upperX + (upperX / 10), lowerY, text,
                15);
        d.setColor(Color.WHITE);
        d.drawText(upperX + (upperX / 10), lowerY, text,
                15);
    }

    /**
     * draws winner background animation.
     *
     * @param d drawsurface
     */
    public void drawWinnerBackground(DrawSurface d) {
        //background
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //random stars with random colors
        for (int i = 0; i < 100; i++) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            drawOneStar(d, 3, new Color(r, g, b));
        }
    }

    /**
     * draws loser background animation.
     *
     * @param d     drawsurface
     * @param color the color
     */
    public void drawBackground(DrawSurface d, Color color) {
        //background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

    }

    /**
     * Draw mult stars.
     *
     * @param d        the d
     * @param starSize the star size
     * @param color    the color
     * @param stars    the stars
     */
    public static void drawMultStars(DrawSurface d, int starSize, Color color,
                                     int stars) {
        //draw stars
        for (int i = 0; i < stars; i++) {
            drawOneStar(d, 1, color);
        }
    }


    /**
     * Draws random stars on given surface.
     *
     * @param d        draw surface
     * @param starSize for a start
     * @param color    for a star
     */
    public static void drawOneStar(DrawSurface d, int starSize, Color color) {
        //set given color
        d.setColor(color);
        //new randomizer
        Random rand = new Random();
        //set bounds for random position
        int width = d.getWidth();
        int height = d.getHeight();
        //random positions
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        d.fillCircle(x, y, starSize);
    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}
