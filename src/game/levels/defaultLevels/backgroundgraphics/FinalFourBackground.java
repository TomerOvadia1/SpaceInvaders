package game.levels.defaultLevels.backgroundgraphics;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import constants.LevelsConsts;
import sprites.Sprite;

import java.awt.Color;

/**
 * FinalFourBackground Class.
 *
 * @author Tomer Ovadia
 */
public class FinalFourBackground implements Sprite {

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(LevelsConsts.FINAL4_BACKGROUND_COLOR);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int x = (int) LevelsConsts.FINAL4_BACKGROUND_CLOUD.getX();
        int y = (int) LevelsConsts.FINAL4_BACKGROUND_CLOUD.getY();
        drawCloud(x, y, d);
        x = d.getWidth() - x * 2;
        y = d.getHeight() - y / 3;
        drawCloud(x, y, d);

    }

    /**
     * draw cloud sprite.
     *
     * @param x position
     * @param y position
     * @param d draw surface
     */
    public void drawCloud(int x, int y, DrawSurface d) {
        //create lines from a cloud
        int lineX = x;
        int targetY = d.getHeight();
        int targetX = x - 30;
        //loop for required const line number
        for (int i = 0; i < LevelsConsts.FINAL4_BACKGROUND_LINE_NUM; i++) {
            d.setColor(Color.WHITE);
            d.drawLine(lineX, y + i, targetX, targetY);
            lineX += 10;
            targetX += 10;
        }
        d.setColor(new Color(204, 204, 204));
        //first circle
        int radius = 30;
        d.fillCircle(x, y, radius);
        //second circle
        x += radius;
        y += radius;
        d.fillCircle(x, y, radius);
        //third circle
        d.setColor(new Color(170, 170, 170));
        y -= radius;
        x += radius / 2;
        radius += 5;
        d.fillCircle(x, y, radius);
        //fourth circle
        d.setColor(new Color(153, 153, 153));
        y += radius;
        x += radius;
        radius += 5;
        d.fillCircle(x, y, radius);
        //fifth circle
        x -= radius;
        radius = radius - 20;
        y += radius;
        d.fillCircle(x, y, radius);
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
