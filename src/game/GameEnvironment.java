package game;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class contains all the collidable object of the game.
 * It is for manage collisiosn between balls and objects.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * Add a collidable object to the game environment.
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the information about where the closest collision will happen if the ball continues its trajectory.
     * The longest trajectory for the ball is 1000 because the window size is 800x600 (pythagore).
     * @param trajectory the line representing the trajectory of the ball.
     * @return CollisionInfo with the closest collision point and its object if one has been detected, null else.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestP = null;
        Collidable closestObj = null;
        double closestDist = 1000;

        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point p = trajectory.closestIntersectionToStartOfLine(rect);
            if (p != null) {
                double dist = trajectory.start().distance(p);
                if (dist < closestDist) {
                    closestDist = dist;
                    closestP = p;
                    closestObj = c;
                }
            }
        }
        if (closestP == null) {
            return null;
        }
        return new CollisionInfo(closestP, closestObj);
    }

    /**
     * Removes a collidable object from the game.
     * @param c the Collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
