package game;
import geometry.Point;
/**
 * The Velocity class represents the change in position on the x and y axes.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Constructs Velocity object with dx and dy.
     * @param dx the change in x.
     * @param dy the change in y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies this velocity to a given point.
     * @param p the point to move.
     * @return the new point after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * Creates Velocity object with angle and speed.
     * @param angle the direction in degrees.
     * @param speed the intensity of the velocity.
     * @return the velocity created by using angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle);
        double dx = Math.cos(angleRad) * speed;
        double dy = -Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }
}
