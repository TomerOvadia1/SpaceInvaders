package sprites;

import biuoop.DrawSurface;
import constants.Consts;
import game.SpaceInvaderLevel;
import game.listener.Counter;
import geometry.Point;
import java.awt.Color;

/**
 * LivesIndicator Class.
 *
 * @author Tomer Ovadia
 */
public class LivesIndicator implements Sprite {
    private Point position;
    private Counter livesCounter;

    /**
     * Constructor.
     *
     * @param pos   for indicator
     * @param lives counter
     */
    public LivesIndicator(Point pos, Counter lives) {
        this.position = pos;
        this.livesCounter = lives;
    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param surface draw surface to draw object on.
     */
    public void drawOn(DrawSurface surface) {
        //get coordinates of a rec
        int textX = (int) this.position.getX();
        int textY = (int) this.position.getY();
        int textSize = Consts.BLOCK_HIT_FONT_SIZE / 2;
        //EFFECT ------------------------------------------------------------
        //this is just dark gray text used for a visual effect
        surface.setColor(Color.GRAY.darker());
        surface.drawText(textX - 1, textY + textSize,
                Consts.LIVES_TEXT, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 2, textY + textSize,
                Consts.LIVES_TEXT, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 1, textY + 1 + textSize,
                Consts.LIVES_TEXT, Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 2, textY + 1 + textSize,
                Consts.LIVES_TEXT, Consts.BLOCK_HIT_FONT_SIZE);
        //-------------------------------------------------------------------
        surface.setColor(Color.WHITE);
        surface.drawText(textX, textY + textSize,
                Consts.LIVES_TEXT, Consts.BLOCK_HIT_FONT_SIZE);
        //DRAW LIVES
        //this is just for fun
        int x = (int) textX + (Consts.BLOCK_HIT_FONT_SIZE) * 5;
        int y = (int) textY;
        for (int i = 0; i < livesCounter.getValue(); i++) {
            int space = Consts.LIVES_INDICATOR_RADIUS;
            drawLives(surface, x, y);
            x += space * 3;
        }

    }

    /**
     * Draw number of lives left in a graphic way-as red bombs.
     *
     * @param surface draw surface
     * @param x       position
     * @param y       position
     */
    public void drawLives(DrawSurface surface, int x, int y) {
        int radius = Consts.LIVES_INDICATOR_RADIUS;
        surface.setColor(Color.black);
        surface.fillCircle(x, y, radius);
        //EFFECT-------------------------------------------------------------
        surface.setColor(Color.WHITE);
        surface.fillCircle(x - 2, y - 2, 1);
        //
        surface.setColor(Color.getHSBColor(153, 102, 0));
        surface.setColor(Color.red);
        surface.fillCircle(x, y - radius, 1);
        surface.setColor(Color.BLACK);
        surface.fillCircle(x - 1, y - radius - 1, 1);
        surface.fillCircle(x - 2, y - radius - 2, 1);
        surface.setColor(Color.YELLOW);
        surface.fillCircle(x - 3, y - radius - 3, 2);
        //-------------------------------------------------------------------
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, radius);


    }

    /**
     * notify the sprite that time has passed.
     *
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
