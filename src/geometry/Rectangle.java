package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class is for represents rectangle by using its upper left point, its width and its height.
 * It provides methods to get the rectangle's dimensions and to determine intersection points with a line.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Rectangle constructor for creating rectangle with a upper-left point, width and height.
     * @param upperLeft the top left corner point of the rectangle.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the top left corner point of the rectangle.
     */
    public Point getUpLeft() {
        return upperLeft;
    }

    /**
     * Determine the list of intersection points between the rectangle and the given line.
     * @param line to check for intersection with  the rectangle.
     * @return the list of intersection points with the line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Point ul = upperLeft;
        Point ur = new Point(ul.getX() + width, ul.getY());
        Point ll = new Point(ul.getX(), ul.getY() + height);
        Point lr = new Point(ul.getX() + width, ul.getY() + height);

        Line top    = new Line(ul, ur);
        Line bottom = new Line(ll, lr);
        Line left   = new Line(ul, ll);
        Line right  = new Line(ur, lr);

        for (Line edge : List.of(top, bottom, left, right)) {
            Point ip = line.intersectionWith(edge);
            if (ip != null && !points.contains(ip)) {
                points.add(ip);
            }
        }
        return points;
    }
}
