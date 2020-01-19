package collidable;
import biuoop.DrawSurface;
import constants.Consts;
import factories.BlockDrawer;
import game.SpaceInvaderLevel;
import game.listener.HitListener;
import game.listener.HitNotifier;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 *
 * @author Tomer Ovadia
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int DEFAULT_HEIGHT = 1;
    private static final int DEFAULT_WIDTH = 1;
    private static final int DEFAULT_HIT_COUNT = 1;
    private Rectangle rec;
    private Color color;
    private List<HitListener> hitListeners;
    private List<BlockDrawer> blockDrawers;
    private Color stroke = null;
    private int hitCount = 0;

    /**
     * Block constructor used for block factory.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     */
    public Block(int xpos, int ypos) {
        this.rec = new Rectangle(new Point(xpos, ypos), DEFAULT_WIDTH,
                DEFAULT_HEIGHT);
        this.hitListeners = new ArrayList<>();
        this.hitCount = DEFAULT_HIT_COUNT;
    }

    /**
     * Block constructor - using a rectangle and color.
     *
     * @param rectangle object for a block
     * @param color     for a block
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rec = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Block constructor - using an upper left point , height ,width and
     * color.
     *
     * @param upperLeft Point for the block.
     * @param width     of a block.
     * @param height    height of a block.
     * @param color     of a block.
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the current Block Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Notify a block object on a hit . as a result function return an updated
     * velocity , according to the position of the collision.
     *
     * @param hitter          ball
     * @param collisionPoint  Point of intersection
     * @param currentVelocity of an Object
     * @return correct ball's velocity after a hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //check if hit occurred on top or bottom edges or on sides to return
        //the proper velocity
        if (this.rec.leftEdge().pointCheck(collisionPoint)
                || this.rec.rightEdge().pointCheck(collisionPoint)) {
            dx = -dx;
        }
        if (this.rec.upperEdge().pointCheck(collisionPoint)
                || this.rec.lowerEdge().pointCheck(collisionPoint)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Gets color.
     *
     * @return color of a block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws block on given DrawSurface.
     *
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        //get coordinates of a block
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        //get block width and height
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        if (this.blockDrawers != null) {
            if (this.getHitCount() == 0) {
                return;
            }
            this.blockDrawers.get(this.getHitCount() - 1).draw(surface,
                    this.getCollisionRectangle());
            //draw stroke if exists.
            if (stroke != null) {
                surface.setColor(this.stroke);
                surface.drawRectangle(x, y, width, height);
            }
        } else {
            this.defaultDrawOn(surface);
        }
    }

    /**
     * Default draw on.
     *
     * @param surface the surface
     */
    public void defaultDrawOn(DrawSurface surface) {
        //get coordinates of a block
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        //get block width and height
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();

        //fill block
        surface.setColor(this.getColor());
        surface.fillRectangle(x, y, width, height);
        //draw rectangle edges
        //if hit count is negative we don't draw it (for backgrounds)
        if (this.hitCount < 0) {
            return;
        }
        //draw hit number
        //this.drawHitCount(surface);
    }

    /**
     * Draws hit counter on Block center point.
     *
     * @param surface to draw on
     */
    public void drawHitCount(DrawSurface surface) {
        //get coordinates of a block
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        //get block width and height
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        //get center position inside a block
        int centerX = x + (width / 2);
        int centerY = y + (height / 2);
        //set a string to draw
        String hit;
        //if hit count is 0 we draw an X , as required
        if (this.hitCount == 0) {
            hit = "X";
        } else { //else draw the number
            hit = Integer.toString(this.hitCount);
        }
        //EFFECT ------------------------------------------------------------
        //black text for effect
        surface.setColor(Color.BLACK);
        //this is used to fix the text position on a block
        int textSize = Consts.BLOCK_HIT_FONT_SIZE / 2;
        surface.drawText(centerX - 1, centerY - 1 + textSize,
                hit, Consts.BLOCK_HIT_FONT_SIZE);
        //-------------------------------------------------------------------
        //hit counter drawing
        surface.setColor(Color.WHITE);
        surface.drawText(centerX, centerY + textSize,
                hit, Consts.BLOCK_HIT_FONT_SIZE);
    }


    /**
     * Notify a block for a time pass.(currently empty).
     *
     * @param dt delta time
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * Adds a block object to game class - adds a block to sprite and
     * collidable collections.
     *
     * @param g SpaceInvaderLevel object to add ball to.
     */
    public void addToGame(SpaceInvaderLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Set a new hit count for a block.
     *
     * @param num new hit count
     */
    public void setHitCount(int num) {
        this.hitCount = num;
    }

    /**
     * Gets hit count.
     *
     * @return this block hit count value.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * removes block from given game.
     *
     * @param spaceInvaderLevel to remove from.
     */
    public void removeFromGame(SpaceInvaderLevel spaceInvaderLevel) {
        spaceInvaderLevel.removeCollidable(this);
        spaceInvaderLevel.removeSprite(this);
    }

    /**
     * @return hit listeners for current block.
     */
    private List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl object to add
     */
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl object to remove
     */
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }

    /**
     * notify hit to all listeners.
     *
     * @param hitter ball object which collided with block.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners =
                new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets width.
     *
     * @return this block width.
     */
    public double getWidth() {
        return this.rec.getWidth();
    }

    /**
     * Set block color.
     *
     * @param c to set
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Set block height.
     *
     * @param blockHeight to set.
     */
    public void setHeight(int blockHeight) {
        this.rec = new Rectangle(this.rec.getUpperLeft(), this.rec.getWidth(),
                blockHeight);
    }

    /**
     * Set block width.
     *
     * @param blockWidth to set.
     */
    public void setWidth(int blockWidth) {
        this.rec = new Rectangle(this.rec.getUpperLeft(), blockWidth,
                this.rec.getHeight());
    }

    /**
     * Set block drawers.
     *
     * @param blockDrawerList to set.
     */
    public void setBlockDrawers(List<BlockDrawer> blockDrawerList) {
        this.blockDrawers = blockDrawerList;
    }

    /**
     * Set block stroke.
     *
     * @param strokeColor to set.
     */
    public void setStroke(Color strokeColor) {
        this.stroke = strokeColor;
    }

    /**
     * Sets rec.
     *
     * @param rectangle the rectangle
     */
    public void setRec(Rectangle rectangle) {
        this.rec = rectangle;
    }
}
