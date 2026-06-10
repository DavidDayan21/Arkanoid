package sprites;

import engine.Keyboard;
import engine.Surface;
import game.Collidable;
import game.Game;
import game.GameConfig;
import game.Sprite;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

public class ReversePaddle implements Sprite, Collidable {
    private Rectangle rect;
    private final int speed;
    private Keyboard keyboard;
    private boolean reversePhysics;

    public ReversePaddle(Keyboard keyboard, boolean reversePhysics, int width, int speed) {
        this.keyboard = keyboard;
        this.reversePhysics = reversePhysics;
        this.speed = speed;
        int x = (GameConfig.WIDTH - width) / 2;
        this.rect = new Rectangle(new Point(x, 560), width, 20);
    }

    public void timePassed() {
        boolean left  = keyboard.isPressed(Keyboard.LEFT_KEY);
        boolean right = keyboard.isPressed(Keyboard.RIGHT_KEY);
        int wrap = GameConfig.WIDTH - 2 * GameConfig.BORDER;
        double x = rect.getUpLeft().getX();
        double y = rect.getUpLeft().getY();
        if (left)  x = (x + speed) % wrap;
        if (right) x = (x - speed + wrap) % wrap;
        rect = new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight());
    }

    public void drawOn(Surface d) {
        int x = (int) rect.getUpLeft().getX();
        int y = (int) rect.getUpLeft().getY();
        int w = (int) rect.getWidth();
        int h = (int) rect.getHeight();
        d.setColor(game.Settings.getTheme().paddle());
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);
    }

    public Rectangle getCollisionRectangle() { return rect; }

    public Velocity hit(sprites.Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double spd = Math.hypot(dx, dy);
        double left  = rect.getUpLeft().getX();
        double top   = rect.getUpLeft().getY();
        double right = left + rect.getWidth();
        double cx = collisionPoint.getX(), cy = collisionPoint.getY();
        double eps = 0.0001;

        if (Math.abs(cx - left) < eps || Math.abs(cx - right) < eps) {
            return new Velocity(-dx, dy);
        }
        if (Math.abs(cy - top) < eps) {
            double regionWidth = rect.getWidth() / 5.0;
            double relX = cx - left;
            int region = (int)(relX / regionWidth) + 1;

            if (reversePhysics) {
                region = 6 - region;
            }

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

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
