package collidable;

import geometry.Point;

/**
 * CollisionInfo Class.
 * this class is used to store the collision's information :
 * -Point of collision
 * -Collision object
 *
 * @author Tomer Ovadia
 */
public class CollisionInfo {
    private Collidable obj;
    private Point collision;

    /**
     * Collision info constructor.
     *
     * @param c         object that appeared in collision.
     * @param collision Point of collision
     */
    public CollisionInfo(Collidable c, Point collision) {
        this.obj = c;
        this.collision = collision;
    }

    /**
     * Collision point point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.obj;
    }
}