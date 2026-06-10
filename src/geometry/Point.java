package geometry;
/**
 * Create a point with x and y coordinates.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a point with x and y coordinates.
     * @param x the x coordinate of the point.
     * @param y the y coordinate  of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another.
     * @param other the other point to compare with.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
     }

    /**
     * Checks if this point is equal to another point by using a threshold because of the floating point precision.
     * @param other the other point to compare with.
     * @return  true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < 0.0000001 && Math.abs(this.y - other.y) < 0.0000001);
     }

    /**
     * @return the x coordinate value.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y coordinate value.
     */
    public double getY() {
        return y;
    }
}