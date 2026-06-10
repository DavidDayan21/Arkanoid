package sprites;

import engine.Surface;
import game.Collidable;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

public class CollidablePaddle implements Collidable {
    private int x, y, w, h;

    public CollidablePaddle(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.w = w; this.h = h;
    }

    public void setX(int x) { this.x = x; }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(new Point(x, y), w, h);
    }

    @Override
    public Velocity hit(sprites.Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double spd = Math.hypot(dx, dy);
        double left  = x, top = y, right = x + w;
        double cx = collisionPoint.getX(), cy = collisionPoint.getY();
        double eps = 0.0001;

        if (Math.abs(cx - left) < eps || Math.abs(cx - right) < eps) {
            return new Velocity(-dx, dy);
        }
        if (Math.abs(cy - top) < eps) {
            double regionWidth = w / 5.0;
            double relX = cx - left;
            int region = (int)(relX / regionWidth) + 1;
            switch (region) {
                case 1: return Velocity.fromAngleAndSpeed(150, spd);
                case 2: return Velocity.fromAngleAndSpeed(120, spd);
                case 3: return new Velocity(dx, -dy);
                case 4: return Velocity.fromAngleAndSpeed(60,  spd);
                case 5: return Velocity.fromAngleAndSpeed(30,  spd);
                default: return new Velocity(dx, -dy);
            }
        }
        return new Velocity(dx, -dy);
    }

    public void drawOn(Surface d) {
        d.setColor(game.Settings.getTheme().paddle());
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);
    }
}
