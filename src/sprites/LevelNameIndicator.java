package sprites;

import biuoop.DrawSurface;
import constants.Consts;
import game.SpaceInvaderLevel;
import geometry.Point;
import java.awt.Color;

/**
 * LevelNameIndicator Class.
 *
 * @author Tomer Ovadia
 */
public class LevelNameIndicator implements Sprite {
    private Point position;
    private String levelName;

    /**
     * Constructor.
     *
     * @param pos  position for level name indicator
     * @param name of level
     */
    public LevelNameIndicator(Point pos, String name) {
        this.position = pos;
        this.levelName = name;
    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param surface draw surface to draw object on.
     */
    public void drawOn(DrawSurface surface) {

        int textX = (int) this.position.getX();
        int textY = (int) this.position.getY();
        int textSize = Consts.BLOCK_HIT_FONT_SIZE / 2;
        //EFFECT ------------------------------------------------------------
        //this is just dark gray text used for a visual effect
        surface.setColor(Color.GRAY.darker());
        surface.drawText(textX - 1, textY + textSize,
                Consts.LEVEL_NAME + this.levelName,
                Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 2, textY + textSize,
                Consts.LEVEL_NAME + this.levelName,
                Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 1, textY + 1 + textSize,
                Consts.LEVEL_NAME + this.levelName,
                Consts.BLOCK_HIT_FONT_SIZE);
        surface.drawText(textX - 2, textY + 1 + textSize,
                Consts.LEVEL_NAME + this.levelName,
                Consts.BLOCK_HIT_FONT_SIZE);
        //-------------------------------------------------------------------
        surface.setColor(Color.WHITE);
        surface.drawText(textX, textY + textSize,
                Consts.LEVEL_NAME + this.levelName,
                Consts.BLOCK_HIT_FONT_SIZE);

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
