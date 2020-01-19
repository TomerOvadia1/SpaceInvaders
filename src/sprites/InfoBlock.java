package sprites;

import biuoop.DrawSurface;
import game.SpaceInvaderLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * InfoBlock Class.
 *
 * @author Tomer Ovadia
 */
public class InfoBlock implements Sprite {
    private Rectangle rec;
    private java.awt.Color color;

    /**
     * Information Block constructor - using a rectangle and color.
     *
     * @param rectangle object for a block
     * @param color     for a block
     */
    public InfoBlock(Rectangle rectangle, java.awt.Color color) {
        this.rec = rectangle;
        this.color = color;
    }

    /**
     * Info Block constructor - using an upper left point ,
     * height ,width and color.
     *
     * @param upperLeft Point for the block.
     * @param width     of a block.
     * @param height    height of a block.
     * @param color     of a block.
     */
    public InfoBlock(Point upperLeft, double width, double height,
                     java.awt.Color color) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.color = color;
    }


    /**
     * Gets color.
     *
     * @return info block color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets rec.
     *
     * @return info block rectangle.
     */
    public Rectangle getRec() {
        return rec;
    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        //get coordinates of a block
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        //get block width and height
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        //fill block
        d.setColor(this.getColor());
        d.fillRectangle(x, y, width, height);
        //draw rectangle edges
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
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
