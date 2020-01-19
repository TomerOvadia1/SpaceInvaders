package game.levels.defaultLevels.backgroundgraphics;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import constants.LevelsConsts;
import sprites.Sprite;

import java.awt.Color;

/**
 * GreenThreeBackground Class.
 *
 * @author Tomer Ovadia
 */
public class GreenThreeBackground implements Sprite {

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        //background
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //building
        d.setColor(LevelsConsts.GREEN3_BACKGROUND_BUILDING_COLOR);
        //bottom building block
        int x = (int) LevelsConsts.GREEN3_BACKGROUND_BUILDING_POINT.getX();
        int y = (int) LevelsConsts.GREEN3_BACKGROUND_BUILDING_POINT.getY();
        int width = LevelsConsts.GREEN3_BACKGROUND_BUILDING_WIDTH;
        int height = LevelsConsts.GREEN3_BACKGROUND_BUILDING_HEIGHT;
        //some shading
        d.setColor(LevelsConsts.GREEN3_BACKGROUND_BUILDING_COLOR.brighter());
        d.fillRectangle(x - 3, y - 3, width, height + 3);
        //the building
        d.setColor(LevelsConsts.GREEN3_BACKGROUND_BUILDING_COLOR);
        d.fillRectangle(x, y, width, height);
        //windows
        width = LevelsConsts.GREEN3_BACKGROUND_WINDOWS_WIDTH;
        height = LevelsConsts.GREEN3_BACKGROUND_WINDOWS_HEIGHT;
        int widthDiff = width / 2;
        int heightDiff = height / 3;
        int startX = x;
        y += heightDiff;
        for (int i = 0; i < LevelsConsts.GREEN3_BACKGROUND_WINDOWS; i++) {
            x = startX;
            for (int j = 0; j < LevelsConsts.GREEN3_BACKGROUND_WINDOWS - 1;
                 j++) {
                x += widthDiff;
                d.setColor(Color.WHITE);
                d.fillRectangle(x, y, width, height);
                //shading
                d.setColor(new Color(151, 151, 151));
                d.fillRectangle(x + width / 6, y + height / 3, 1,
                        height / 3);
                //skip between windows
                x += width;

            }
            y += height + heightDiff;

        }
        //upper building block
        x = (int) LevelsConsts.GREEN3_BACKGROUND_BUILDING_POINT.getX()
                + LevelsConsts.GREEN3_BACKGROUND_BUILDING_WIDTH / 3;
        y = (int) LevelsConsts.GREEN3_BACKGROUND_BUILDING_POINT.getY()
                - LevelsConsts.GREEN3_BACKGROUND_BUILDING_HEIGHT / 3;
        width = LevelsConsts.GREEN3_BACKGROUND_BUILDING_WIDTH / 3;
        height = LevelsConsts.GREEN3_BACKGROUND_BUILDING_HEIGHT / 3;
        d.setColor(LevelsConsts.GREEN3_BACKGROUND_BUILDING_COLOR);
        d.fillRectangle(x, y, width, height);
        //pole
        x += width / 3;
        y = y - height - LevelsConsts.GREEN3_BACKGROUND_BUILDING_HEIGHT;
        height += LevelsConsts.GREEN3_BACKGROUND_BUILDING_HEIGHT;
        width = width / 3;
        d.fillRectangle(x, y, width, height);
        //lighting
        x += (width / 3) + 1;
        Color[] colors = {Color.ORANGE, Color.RED, Color.WHITE};
        int radius = LevelsConsts.GREEN3_BACKGROUND_LIGHT_RADIUS;
        for (int i = 0; i < 3; i++) {
            d.setColor(colors[i]);
            d.fillCircle(x, y, radius);
            radius = radius - 3;
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
