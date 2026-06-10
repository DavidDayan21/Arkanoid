package sprites;
import geometry.Point;
import geometry.Line;
import game.Collidable;
import game.CollisionInfo;
import game.GameEnvironment;
import game.Sprite;
import game.Velocity;
import java.awt.Color;
import engine.Surface;

public class Ball implements Sprite {

    private Point center;
    private int rad;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment environment;

    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.rad = r;
        this.color = color;
    }

    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    public int getX() { return (int) this.center.getX(); }
    public int getY() { return (int) this.center.getY(); }
    public int getSize() { return this.rad; }
    public java.awt.Color getColor() { return this.color; }

    public void drawOn(Surface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.rad);
    }

    public void setVelocity(Velocity v) { this.velocity = v; }
    public void setVelocity(double dx, double dy) { this.velocity = new Velocity(dx, dy); }
    public Velocity getVelocity() { return this.velocity; }

    public void setGameEnvironment(GameEnvironment env) { this.environment = env; }

    public void moveOneStep() {
        Point start = this.center;
        Point end = this.velocity.applyToPoint(start);
        Line trajectory = new Line(start, end);

        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info == null) {
            this.center = end;
        } else {
            Point cp = info.collisionPoint();
            Velocity current = this.velocity;
            double dx = current.getDx();
            double dy = current.getDy();
            double speedNorm = Math.sqrt(dx * dx + dy * dy);

            double eps  = 0.001;
            double backX = cp.getX() - (dx / speedNorm) * eps;
            double backY = cp.getY() - (dy / speedNorm) * eps;
            this.center = new Point(backX, backY);

            Collidable col = info.collisionObject();
            Velocity newVelocity = col.hit(this, cp, current);
            this.velocity = newVelocity;
        }
    }

    public void setColor(Color color) { this.color = color; }

    public void removeFromGame(game.Game g) { g.removeSprite(this); }

    public void timePassed() { this.moveOneStep(); }
}
