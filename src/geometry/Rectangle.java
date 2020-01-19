package geometry;

import java.util.ArrayList;
import java.util.List;


/**
 * Rectangle class.
 *
 * @author Tomer Ovadia
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft point for upper left corner
     * @param width     of a rectangle
     * @param height    of a rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        //if user entered negative values  for width or height
        //check if <= is necessery
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Cannot use negative values"
                    + "for height/width.");
        }
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line to check intersection with
     * @return a List of intersection point with specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //new Point array
        List<Point> l1 = new ArrayList<>();
        Line[] rectangleLines = new Line[4];
        //Create an array for all edge lines of a rectangle.
        rectangleLines[0] = this.upperEdge();
        rectangleLines[1] = this.lowerEdge();
        rectangleLines[2] = this.rightEdge();
        rectangleLines[3] = this.leftEdge();
        //check for intersection
        for (Line edge : rectangleLines) {
            if (line.isIntersecting(edge)) {
                l1.add(line.intersectionWith(edge));
            }
        }
        return l1;
    }

    /**
     * Gets width.
     *
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Upper edge line.
     *
     * @return Line of the upper edge of a rectangle.
     */
    public Line upperEdge() {
        Point upperRight = new Point(this.upperLeft.getX()
                + this.getWidth(), this.upperLeft.getY());
        return new Line(this.getUpperLeft(), upperRight);
    }

    /**
     * Lower edge line.
     *
     * @return Line of the lower edge of a rectangle.
     */
    public Line lowerEdge() {
        Point lowerLeft = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.getHeight());
        Point lowerRight = new Point(lowerLeft.getX() + this.getWidth(),
                lowerLeft.getY());
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * Left edge line.
     *
     * @return Line of the left edge of a rectangle.
     */
    public Line leftEdge() {
        Point lowerLeft = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.getHeight());
        return new Line(this.upperLeft, lowerLeft);
    }

    /**
     * Right edge line.
     *
     * @return Line of the right edge of a rectangle.
     */
    public Line rightEdge() {
        Point upperRight = new Point(this.upperLeft.getX()
                + this.getWidth(), this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(),
                this.upperLeft.getY() + this.getHeight());
        return new Line(upperRight, lowerRight);
    }
}