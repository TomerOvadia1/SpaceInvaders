package sprites;

import geometry.Point;

/**
 * Velocity class.
 *
 * @author Tomer Ovadia
 */
public class Velocity {
    //private Point direction; (?)
    private double dx;
    private double dy;

    /**
     * Velocity object Constructor.
     *
     * @param dx delta X
     * @param dy delta Y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return delta X
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return delta Y
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Assign velocity from given angle and movement speed .
     * Up angle is 0deg as specified.
     *
     * @param angle of the movement (the slope) in degrees.
     * @param speed of the object (number of units in angle's direction )
     * @return new Velocity object with matching dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        /* Up angle is 0deg as specified therefore (-angle+90)
        point (0,0) is at the top left corner , thus the added minus before
        sin(angle).
        slope = tan(angle) = sin(angle)/cos(angle) = dy/dx
        */
        double dx = Math.cos(Math.toRadians(-angle + 90)) * speed;
        if (angle % 180 == 0) {
            dx = 0;
        }
        double dy = -Math.sin(Math.toRadians(-angle + 90)) * speed;

        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p  current Point
     * @param dt the dt
     * @return Point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt,
                p.getY() + this.dy * dt);
    }


}