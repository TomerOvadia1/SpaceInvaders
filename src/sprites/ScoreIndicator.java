package sprites;

import biuoop.DrawSurface;
import constants.Consts;
import game.SpaceInvaderLevel;
import game.listener.Counter;
import geometry.Point;

import java.awt.Color;

/**
 * ScoreIndicator Class.
 *
 * @author Tomer Ovadia.
 */
public class ScoreIndicator implements Sprite {
    private Point position;
    private Counter scoreCounter;

    /**
     * ScoreIndicator constructor - using a rectangle and color.
     *
     * @param pos   text position
     * @param score counter
     */
    public ScoreIndicator(Point pos, Counter score) {
        this.position = pos;
        this.scoreCounter = score;
    }


    /**
     * Draws hit counter on Block center point.
     *
     * @param surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        //get coordinates of a rec
        int x = (int) this.position.getX();
        int y = (int) this.position.getY();
        //set a string to draw
        String scoreString = Consts.SCORE_TEXT
                + Integer.toString(this.scoreCounter.getValue());
        //this is used to fix the text position on a block
        int textSize = Consts.BLOCK_HIT_FONT_SIZE / 2;

        //EFFECT ------------------------------------------------------------
        //this is just dark gray text used for a visual effect
        surface.setColor(Color.GRAY.darker());
        surface.drawText(x - 1, y + textSize,
                scoreString, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(x - 2, y + textSize,
                scoreString, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(x - 1, y + 1 + textSize,
                scoreString, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(x - 2, y + 1 + textSize,
                scoreString, Consts.BLOCK_HIT_FONT_SIZE);

        //-------------------------------------------------------------------
        //Score counter drawing
        surface.setColor(Color.WHITE);
        surface.drawText(x, y + textSize,
                scoreString, Consts.BLOCK_HIT_FONT_SIZE);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt delta time
     */
    public void timePassed(double dt) {

    }

    /**
     * Add a sprite object to the game class.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    public void addToGame(SpaceInvaderLevel g) {
        g.addSprite(this);
    }
}
