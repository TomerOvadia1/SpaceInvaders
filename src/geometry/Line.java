package geometry;

import java.util.List;

/**
 * Line class.
 *
 * @author Tomer Ovadia
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Line constructor sending Point object to create a Line from.
     * Note: Constructor takes the min x value as start
     *
     * @param start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Line constructor sending double values(2 points coordinates)
     * to create a new Line.
     * Note: Constructor takes points as they were sent.
     *
     * @param x1 start point X coordinate
     * @param y1 start point Y coordinate
     * @param x2 end point X coordinate
     * @param y2 end point Y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        //set start point
        this.start = new Point(x1, y1);
        //set end point
        this.end = new Point(x2, y2);
        /*
        if (this.start.equals(this.end)) {
            throw new RuntimeException("Cannot create a line from"
                    + " equal points.");

        }
        */
    }

    /**
     * Length double.
     *
     * @return length of the Line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Middle point.
     *
     * @return the mid Point of the line
     */
    public Point middle() {
        //get delta X and delta Y
        double deltaX = this.start.getX() + this.end.getX();
        double deltaY = this.start.getY() + this.end.getY();
        //add half of the distance between start and end points
        deltaX = deltaX / 2;
        deltaY = deltaY / 2;
        //return mid point
        return new Point(deltaX, deltaY);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other line for intersection check
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }


    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     * NOTE: if 2 lines are equal or have more than 1 intersection point
     * function return null.
     *
     * @param other line to check intersection with
     * @return intersection point (as Point) if exists or null otherwise
     */
    public Point intersectionWith(Line other) {
        //create new line equation object
        LineEquation currEquation = new LineEquation(this);
        LineEquation otherEquation = new LineEquation(other);
        //checks if both lines are vertical and have the same equation
        boolean bothVertical = (currEquation.isInfiniteSlope()
                && otherEquation.isInfiniteSlope()
                && currEquation.getxIntercept()
                == otherEquation.getxIntercept());
        //if lines are co linear
        if (bothVertical
                || (currEquation.getSlope() == otherEquation.getSlope())) {
            //if they are equal they have infinite intersection points
            if (this.equals(other)) {
                return null;
            }
            //if lines are co linear the intersection must be on start/end
            //points, otherwise they merge.
            if (this.start.equals(other.start)
                    || this.start.equals(other.end)) {
                return this.start;
            }
            //if they intersect only in end point
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }
            //more than 1 intersection point
            return null;
        }
        double intersectionX;
        double intersectionY;
        //if this line is vertical and other line isn't
        if (currEquation.isInfiniteSlope()
                && !otherEquation.isInfiniteSlope()) {
            //get intersection X
            intersectionX = currEquation.getxIntercept();
            //get intersection Y
            intersectionY = otherEquation.getSlope() * intersectionX
                    + otherEquation.getyIntercept();
            //if other line is vertical and this line isn't
        } else if (!currEquation.isInfiniteSlope()
                && otherEquation.isInfiniteSlope()) {
            //get intersection X
            intersectionX = otherEquation.getxIntercept();
            //get intersection Y
            intersectionY = currEquation.getSlope() * intersectionX
                    + currEquation.getyIntercept();

        } else {    //both aren't vertical
            // mX1+b1=mX2+b2 (from line equation : Y=mX+b)
            double b1 = currEquation.getyIntercept();
            double b2 = otherEquation.getyIntercept();
            double m1 = currEquation.getSlope();
            double m2 = otherEquation.getSlope();
            intersectionX = (b2 - b1) / (m1 - m2);
            intersectionY = m1 * intersectionX + b1;
        }
        Point intersection = new Point(intersectionX, intersectionY);
        //if point is on both line segments
        if (this.pointCheck(intersection) && other.pointCheck(intersection)) {
            return intersection;
        }
        //else return null
        return null;

    }

    /**
     * check if two line segments are equal.
     *
     * @param other line , to check equality with
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //if start and end points are equal
        if (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end)
                && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * checks if Point 'check' is on current line segment.
     *
     * @param check point to check
     * @return true if point is one line segment, false otherwise.
     */
    public boolean pointCheck(Point check) {
        Point p1 = this.start();
        Point p2 = this.end();
        //if point is between start and end points
        if (check.getX() <= max(p1.getX(), p2.getX())
                && check.getX() >= min(p1.getX(), p2.getX())
                && check.getY() <= max(p1.getY(), p2.getY())
                && check.getY() >= min(p1.getY(), p2.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Return maximum value between 2 values.
     *
     * @param x1 value for comparison
     * @param x2 value for comparison
     * @return max value
     */
    public double max(double x1, double x2) {
        if (x1 > x2) {
            return x1;
        }
        return x2;
    }

    /**
     * Returns minimum value between 2 values.
     *
     * @param x1 value for comparison
     * @param x2 value for comparison
     * @return min value
     */
    public double min(double x1, double x2) {
        if (x1 > x2) {
            return x2;
        }
        return x1;
    }

    /**
     * Returns the closest intersection Point to the start of the line
     * (if it exists).
     *
     * @param rect to check intersection with
     * @return null If this line does not intersect with the rectangle.
     * Otherwise, return the closest intersection point to the start of
     * the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get a list of intersection Points.
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        //set a starter closetPoint value
        Point closestPoint = intersections.get(0);
        //set a starter min value
        double min = closestPoint.distance(this.start());
        //iterate to find the closest point
        for (int i = 0; i < intersections.size(); i++) {
            Point curr = intersections.get(i);
            if (curr.distance(this.start) < min) {
                min = curr.distance(this.start);
                closestPoint = curr;
            }
        }
        return closestPoint;
    }

}