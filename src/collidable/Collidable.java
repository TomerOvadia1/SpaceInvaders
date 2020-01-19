package collidable;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * Collidable Interface.
 *
 * @author Tomer Ovadia
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter          ball object.
     * @param collisionPoint  Point of intersection
     * @param currentVelocity of an Object
     * @return the new velocity expected after the hit (based on the force the
     * object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}