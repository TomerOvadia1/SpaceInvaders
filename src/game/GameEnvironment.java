package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 * GameEnvironment object is used to store all collidable objects on a game.
 *
 * @author Tomer Ovadia
 */
public class GameEnvironment {
    private List<Collidable> collidableObj;

    /**
     * SpaceInvaderLevel Environment constructor , creating a new list of
     * Collidable
     * Objects.
     */
    public GameEnvironment() {
        this.collidableObj = new ArrayList<>();
    }

    /**
     * Gets collidable obj.
     *
     * @return a list of collidable objects on SpaceInvaderLevel Environment.
     */
    public List getCollidableObj() {
        return this.collidableObj;
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.collidableObj.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidable's
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory of a ball.
     * @return information about the closest collision of an object :
     * collision object,collision point. returns null if no collision occurred .
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = trajectory.end();
        boolean collide = false;
        Collidable collisionObj = null;
        // Make a copy of the collidableObj before iterating over them.
        List<Collidable> collidableList = new ArrayList<>(this.collidableObj);
        //iterate on all collidable objects on game environment
        for (Object obj : collidableList) {
            //downcast
            Collidable shape = (Collidable) obj;
            //get the collision rectangle
            Rectangle rec = shape.getCollisionRectangle();
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(rec);
            if (collisionPoint == null) {
                continue;
            }
            if (collisionPoint.distance(trajectory.start())
                    <= closestPoint.distance(trajectory.start())) {
                closestPoint = collisionPoint;
                collide = true;
                collisionObj = shape;
            }
        }

        //if the object won't collide with any collidable shape
        if (!collide) {
            return null;
        }
        //return the collision information
        return new CollisionInfo(collisionObj, closestPoint);

    }

}