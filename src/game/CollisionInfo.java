package game;
import geometry.Point;

/**
 * CollisionInfo class have information about a collision.
 * The point of the collision occurred and the object that was hit.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * CollisionInfo constructor with a given collision point and a given object.
     * @param collisionPoint the point of the collision.
     * @param collisionObject the object of the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the collision Point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collision Object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
