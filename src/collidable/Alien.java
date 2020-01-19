package collidable;


import factories.BlockDrawer;
import factories.BlockImageDrawer;
import game.listener.AlienListener;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Alien Class.
 * @author Tomer Ovadia
 */
public class Alien extends Block {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    private List<AlienListener> alienListeners;

    /**
     * Instantiates a new Alien.
     *
     * @param x the x
     * @param y the y
     */
    public Alien(int x, int y) {
        //set default settings
        super(x, y);
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setHitCount(1);
        InputStream is = null;
        //load alien image
        try {
            is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("block_images/enemy.png");
            Image image = ImageIO.read(is);
            //add a drawer
            List<BlockDrawer> blockDrawerList = new ArrayList<>();
            BlockImageDrawer blockImageDrawer = new BlockImageDrawer(image);
            blockDrawerList.add(blockImageDrawer);
            this.setBlockDrawers(blockDrawerList);
            is.close();
        } catch (IOException ex) {
            throw new RuntimeException("Error: Cannot load Alien image.");
        }
        this.alienListeners = new ArrayList<>();
    }

    /**
     * Move step by required dx or dy.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void moveStep(double dx, double dy) {
        Rectangle rec = this.getCollisionRectangle();
        double x = rec.getUpperLeft().getX() + dx;
        double y = rec.getUpperLeft().getY() + dy;
        double width = rec.getWidth();
        double height = rec.getHeight();
        this.setRec(new Rectangle(new Point(x, y), width, height));
        return;
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.getCollisionRectangle();
    }

    /**
     * Override given method to notify a hit on an alien.
     *
     * @param hitter          ball
     * @param collisionPoint  Point of intersection
     * @param currentVelocity of an Object
     * @return correct ball's velocity after a hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * notify a hit event.
     *
     * @param hitter ball object which collided with Alien.
     */
    @Override
    public void notifyHit(Ball hitter) {
        super.notifyHit(hitter);
        // Make a copy of the hitListeners before iterating over them.
        List<AlienListener> listeners =
                new ArrayList<>(this.alienListeners);
        // Notify all listeners about a hit event:
        for (AlienListener l1 : listeners) {
            l1.hitEvent(this, hitter);
        }
    }

    /**
     * Add alien listener.
     *
     * @param al the al
     */
    public void addAlienListener(AlienListener al) {
        this.alienListeners.add(al);
    }

    /**
     * Remove alien listener.
     *
     * @param a1 the a 1
     */
    public void removeAlienListener(AlienListener a1) {
        this.alienListeners.remove(a1);
    }


}
