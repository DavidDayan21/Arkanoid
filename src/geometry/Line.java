package geometry;
import java.util.List;

/**
 * Create a line segment by using a start and end point.
 * Setup methods calculating for differents informations about
 * the lines.
 */

public class Line {
    private Point start;
    private Point end;

    /**
     * Create a line by using two points.
     * @param start the starting point of the line.
     * @param end end the ending point of the line.
     */
    public Line(Point start, Point end) {
        // To create the start and the end point of the line
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line using coordinates for the start and end points.
     * @param x1 the coordinate x of the start point.
     * @param y1 the coordinate  y of the start point.
     * @param x2 the coordinate x of the end point.
     * @param y2 the coordinate y of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length between the start and end points
     */
    public double length() {
        // Ask the distance betwen the start and the end point to have the lenght
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point between start and end.
     */
    public Point middle() {
        // Caculation of coordinates for the middle point
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     * @param other the other line to check .
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * Checks if this line intersects with two other lines.
     * @param other1 the first line to check.
     * @param other2 the second line to chekc.
     * @return true if this line intersects with both, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (this.isIntersecting(other1) && this.isIntersecting(other2));
    }

    /**
     * Calculates the intersection point if is existing.
     * @param other the other line to check.
     * @return the intersection point, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        // Calculate denominator
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // If the denominator is null, the are parallel or identical, intersection can't exist
        if (Math.abs(denominator) < 0.0000001) { // if denimotator is < 0.0001 is consider as null, thershold
            return null;
        }

        // Determine intersection point
        double px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        Point intersection = new Point(px, py);

        // Check if the intersection point lies within both line segments
        boolean onThis = px >= Math.min(x1, x2) - 0.0000001 && px <= Math.max(x1, x2) + 0.0000001
         && py >= Math.min(y1, y2) - 0.0000001 && py <= Math.max(y1, y2) + 0.0000001;
        boolean onOther = px >= Math.min(x3, x4) - 0.0000001 && px <= Math.max(x3, x4) + 0.0000001
         && py >= Math.min(y3, y4) - 0.0000001 && py <= Math.max(y3, y4) + 0.0000001;


        if (onThis && onOther) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if this line is equal to another by using direction.
     * @param other the other line to check.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start()) && this.end.equals(other.end()))
         || (this.start.equals(other.end()) && this.end.equals(other.start())));
    }

    /**
     * Return the closest intersection point from the start of this line to the given rectangle.
     * Return null if it doesn't exist.
     * @param rect the rectangle to check for intersection with the line.
     * @return the closest intersection point from the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        Point closest = intersections.get(0);
        double minDist = this.start.distance(closest);
        for (Point p : intersections) {
            double d = this.start.distance(p);
            if (d < minDist) {
                minDist = d;
                closest = p;
            }
        }
        return closest;
    }
}