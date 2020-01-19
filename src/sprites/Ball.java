package sprites;
import biuoop.DrawSurface;
import java.awt.Color;
import collidable.Collidable;
import collidable.CollisionInfo;
import game.SpaceInvaderLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;

/**
 * Ball class.
 *
 * @author Tomer Ovadia
 */
public class Ball implements Sprite {

    private int size;
    private Color color;
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Ball constructor sending a point as a center.
     *
     * @param center          point for a ball
     * @param r               radius/size
     * @param color           balls color
     * @param gameEnvironment for a ball
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this(center.getX(), center.getY(), r, color, gameEnvironment);
    }

    /**
     * Ball constructor sending 2 doubles as center point.
     *
     * @param x               coordinate of the ball center.
     * @param y               coordinate of the ball center.
     * @param r               radius/size
     * @param color           balls color
     * @param gameEnvironment for a ball
     */
    public Ball(double x, double y, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        if (r < 0) {
            throw new RuntimeException(
                    "Cannot create a ball with negative radius.");
        }
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        //set default velocity for a ball as a static ball
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return ball x coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return ball y coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return ball size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets color.
     *
     * @return ball color (java.awt.Color object)
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface to draw on (DrawSurface object)
     */
    public void drawOn(DrawSurface surface) {

        //get the color of the ball
        surface.setColor(this.getColor());
        //draw ball
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        //EFFECT-------------------------------------------------------------
        surface.setColor(this.getColor().darker());
        surface.fillCircle(this.getX() + 1, this.getY() + 1,
                this.getSize() - 2);
        surface.setColor(Color.WHITE);
        surface.fillCircle(this.getX() - 2, this.getY() - 2, 1);
        surface.fillCircle(this.getX() - 1, this.getY() - 3, 1);
        //------------------------------------------------------------------
        //draw border
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Set ball velocity given a Velocity object.
     *
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set ball velocity given delta X and delta Y.
     *
     * @param dx delta X
     * @param dy delta Y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return Ball velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Change Ball position by moving its center Point.
     *
     * @param dt the dt
     */
    public void moveOneStep(double dt) {
        //get the balls delta X and delta Y
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        //the next position of the ball
        double nextX = this.center.getX() + dx * dt;
        double nextY = this.center.getY() + dy * dt;
        boolean collisionX = false;
        boolean collisionY = false;
        //create a line from ball center that is parallel to Y axis
        if (dy != 0) {
            collisionY = this.collisionCheck(this.center.getX(), nextY);
        }
        //create a line from ball center that is parallel to X axis
        if (dx != 0) {
            collisionX = this.collisionCheck(nextX, this.center.getY());
        }
        if (!(collisionX || collisionY) && (dx != 0 || dy != 0)) {
            this.collisionCheck(nextX, nextY);
        }
        //move ball center position with the matching velocity
        this.center = this.getVelocity().applyToPoint(this.center, dt);

    }

    /**
     * create a line from ball center to next x and y coordinates to check
     * for ball's collision with collidable objects.
     *
     * @param nextX coordinate for ball's center
     * @param nextY coordinate for ball's center
     * @return true if collision occurred,false otherwise
     */
    public boolean collisionCheck(double nextX, double nextY) {
        //create a line for each movement in y axis and in x axis separately
        //create a line from current position to next position on Y axis
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                nextX, nextY);
        //get the closest collision(intersection) of the ball's line segment
        CollisionInfo collision =
                this.gameEnvironment.getClosestCollision(trajectory);
        //if collision is null than the ball is not colliding with any object
        if (collision != null) {
            //get the collision object
            Collidable collisionObj = collision.collisionObject();
            //announce object on hit and update ball's velocity
            this.velocity = collisionObj.hit(this,
                    collision.collisionPoint(), this.velocity);
            return true;
        }
        return false;
    }

    /**
     * Announce Ball object for a time pass (Sprite interface).
     *
     * @param dt delta time
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Adds ball to given SpaceInvaderLevel.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    public void addToGame(SpaceInvaderLevel g) {
        g.addSprite(this);
    }

    /**
     * removes ball from given game.
     *
     * @param g to remove from
     */
    public void removeFromGame(SpaceInvaderLevel g) {
        g.removeSprite(this);
    }
}