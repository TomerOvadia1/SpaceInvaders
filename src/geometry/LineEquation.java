package geometry;

/**
 * Class LineEquation.
 * This class is used to store relevant parameters for a line in the form
 * of a line equation - Y=mX+b .
 */
public class LineEquation {
    private double slope;
    private boolean infiniteSlope;
    private double yIntercept;
    private double xIntercept;

    /**
     * Constructor.
     *
     * @param l given line.
     */
    public LineEquation(Line l) {
        //set dx (x2-x1) and dy (y2-y1)
        double dy = l.end().getY() - l.start().getY();
        double dx = l.end().getX() - l.start().getX();
        //if dx=0 than the line is vertical and has an infinite slope
        if (dx == 0) {
            this.infiniteSlope = true;
            this.xIntercept = l.start().getX();
            this.slope = Double.POSITIVE_INFINITY;
            return;
        }
        //otherwise we store the slope and yIntercept (b in line equation)
        this.infiniteSlope = false;
        this.slope = dy / dx;
        this.yIntercept = l.start().getY() - (this.slope * l.start().getX());
    }

    /**
     * Gets slope.
     *
     * @return the line's slope.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * Is infinite slope boolean.
     *
     * @return true if slope is infinite,false otherwise.
     */
    public boolean isInfiniteSlope() {
        return this.infiniteSlope;
    }

    /**
     * Gets intercept.
     *
     * @return y intercept for a line.
     */
    public double getyIntercept() {
        return this.yIntercept;
    }

    /**
     * Gets intercept.
     *
     * @return x intercept for a vertical line.
     */
    public double getxIntercept() {
        return this.xIntercept;
    }
}
