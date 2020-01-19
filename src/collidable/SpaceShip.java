package collidable;

import biuoop.KeyboardSensor;
import game.listener.HitListener;
import geometry.Point;
import sprites.Ball;
import sprites.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * SpaceShip Class.
 * @author Tomer Ovadia
 */
public class SpaceShip extends Paddle {
    private List<HitListener> hitListeners;
    private boolean beenHit = false;


    /**
     * Constructor for a SpaceShip.
     *
     * @param block    to use as a paddle
     * @param keyboard sensor to get user keyboard usage.
     * @param speed    of paddle
     */
    public SpaceShip(Block block, KeyboardSensor keyboard, int speed) {
        super(block, keyboard, speed);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * notify a hit event.
     *
     * @param hitter ball who hitted the current object.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners =
                new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(super.getPaddleBlock(), hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl object to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Set as been hit.
     */
    public void setAsBeenHit() {
        this.beenHit = true;
    }

    /**
     * Been hit boolean.
     *
     * @return the boolean
     */
    public boolean beenHit() {
        return beenHit;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        this.notifyHit(hitter);
        return super.hit(hitter, collisionPoint, currentVelocity);
    }
}
