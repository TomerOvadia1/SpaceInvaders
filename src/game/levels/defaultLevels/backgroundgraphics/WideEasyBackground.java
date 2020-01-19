package game.levels.defaultLevels.backgroundgraphics;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import constants.LevelsConsts;
import sprites.Sprite;

import java.awt.Color;

/**
 * WideEasyBackground class.
 *
 * @author Tomer Ovadia
 */
public class WideEasyBackground implements Sprite {
    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        //background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //get position for sun
        int x = (int) LevelsConsts.WIDE_EASY_BACKGROUND_SUN_CENTER.getX();
        int y = (int) LevelsConsts.WIDE_EASY_BACKGROUND_SUN_CENTER.getY();
        int rightBoundX =
                (int) LevelsConsts.WIDE_EASY_BLOCK_START_POINT.getX();
        int rightBoundY =
                (int) LevelsConsts.WIDE_EASY_BLOCK_START_POINT.getY();
        int lineSpace = d.getWidth()
                / LevelsConsts.WIDE_EASY_BACKGROUND_SUN_LINES;
        d.setColor(new Color(255, 255, 0));
        //draws lines
        for (int i = 0; i < LevelsConsts.WIDE_EASY_BACKGROUND_SUN_LINES; i++) {
            d.drawLine(x, y, rightBoundX, rightBoundY);
            rightBoundX -= lineSpace;
        }
        //draw sun
        x = (int) LevelsConsts.WIDE_EASY_BACKGROUND_SUN_CENTER.getX();
        int radius = 50;
        Color[] sunColor = {new Color(255, 153, 0),
                new Color(255, 204, 0), new Color(255, 255,
                0)};
        for (int i = 0; i < sunColor.length; i++) {
            d.setColor(sunColor[i]);
            d.fillCircle(x, y, radius);
            radius -= 10;
        }
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
