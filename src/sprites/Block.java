package sprites;
import geometry.Point;
import geometry.Rectangle;
import game.Collidable;
import game.Game;
import game.Sprite;
import game.Velocity;
import engine.Surface;
import java.awt.Color;

public class Block implements Collidable, Sprite, listeners.HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private java.util.List<listeners.HitListener> hitListeners = new java.util.ArrayList<>();
    private boolean isDeathRegion = false;
    private boolean isBorder = false;

    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public Rectangle getCollisionRectangle() { return this.rectangle; }

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double leftX   = rectangle.getUpLeft().getX();
        double topY    = rectangle.getUpLeft().getY();
        double rightX  = leftX + rectangle.getWidth();
        double bottomY = topY  + rectangle.getHeight();
        double eps = 1e-7;
        double newDx = dx;
        double newDy = dy;

        if (Math.abs(x - leftX) < eps || Math.abs(x - rightX) < eps) {
            newDx = -dx;
        }
        if (Math.abs(y - topY) < eps || Math.abs(y - bottomY) < eps) {
            newDy = -dy;
        }

        if (this.isDeathRegion) {
            notifyHit(hitter);
            return new Velocity(newDx, newDy);
        }

        if (this.isBorder) {
            return new Velocity(newDx, newDy);
        }

        if (!ballColorMatch(hitter)) {
            hitter.setColor(this.color);
            notifyHit(hitter);
        }

        return new Velocity(newDx, newDy);
    }

    public void drawOn(Surface d) {
        d.setColor(this.color);
        Point ul = rectangle.getUpLeft();
        int x = (int) ul.getX();
        int y = (int) ul.getY();
        int w = (int) rectangle.getWidth();
        int h = (int) rectangle.getHeight();
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);
    }

    public void timePassed() {}

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public void addHitListener(listeners.HitListener hl) { this.hitListeners.add(hl); }
    public void removeHitListener(listeners.HitListener hl) { this.hitListeners.remove(hl); }

    private void notifyHit(Ball hitter) {
        java.util.List<listeners.HitListener> listeners = new java.util.ArrayList<>(this.hitListeners);
        for (listeners.HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    public boolean ballColorMatch(Ball ball) { return this.color.equals(ball.getColor()); }
    public void setColor(Color color) { this.color = color; }

    public void removeFromGame(game.Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    public void setAsDeathRegion() { this.isDeathRegion = true; }
    public void setBorder() { this.isBorder = true; }
}
