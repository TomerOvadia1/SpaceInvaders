package geometry;

/**
 * Point class.
 *
 * @author Tomer Ovadia
 */
public class Point {
    private double x;
    private double y;

    /**
     * Point Constructor.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the point we want to measure distance to
     * @return distance between 2 points
     */
    public double distance(Point other) {
        //sqrt ((x1-x2)^2+((y1-y2)^2)
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Equals boolean.
     *
     * @param other point.
     * @return true if 2 points are equal - false otherwise
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return y coordinate.
     */
    public double getY() {
        return this.y;
    }

}