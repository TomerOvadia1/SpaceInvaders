package game.levels.defaultLevels.backgroundgraphics;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import constants.LevelsConsts;
import sprites.Sprite;

import java.awt.Color;

/**
 * DirectHitBackground class.
 *
 * @author Tomer Ovadia
 */
public class DirectHitBackground implements Sprite {

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        //background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //TARGET
        d.setColor(Color.BLUE);
        int x = (int) LevelsConsts.DIRECT_HIT_BACKGROUND_TARGET_POINT.getX();
        int y = (int) LevelsConsts.DIRECT_HIT_BACKGROUND_TARGET_POINT.getY();
        int radius = 50;
        //draw circles
        for (int i = 1; i <= 3; i++) {
            d.drawCircle(x, y, radius * i);
        }
        //draw lines
        d.setColor(Color.BLUE);
        int lineLen = radius * 4;
        d.drawLine(x, y, x - lineLen, y);
        d.drawLine(x, y, x + lineLen, y);
        d.drawLine(x, y, x, y - lineLen);
        d.drawLine(x, y, x, y + lineLen);


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
