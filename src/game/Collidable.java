package game;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
/**
 * Interface representing objects that can be collided with.
 * Collidable objects can provide shape information and respond to a ball collision.
 */
public interface Collidable {

    /**
     * @return the shape of this object represent by rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Informs the object of a collision with the point of the collision and the velocity of the ball.
     * The object returns the new velocity after the collision.
     * @param hitter the ball that hit
     * @param collisionPoint  the point of the collision
     * @param currentVelocity of the ball before the collision
     * @return the new velocity expected
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
