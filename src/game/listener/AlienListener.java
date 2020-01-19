package game.listener;

import collidable.Alien;
import sprites.Ball;

/**
 * The interface Alien listener.
 * @author Tomer Ovadia
 */
public interface AlienListener {

    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Alien beingHit, Ball hitter);
}
