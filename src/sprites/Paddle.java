package sprites;

import geometry.Point;
import geometry.Rectangle;
import game.Collidable;
import game.Game;
import game.GameConfig;
import game.Sprite;
import game.Velocity;
import java.awt.Color;
import engine.Surface;
import engine.Keyboard;

public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private Color color;
    private Keyboard keyboard;
    private int speed;

    private static final int WRAP_WIDTH = GameConfig.WIDTH - 2 * GameConfig.BORDER;

    public Paddle(Keyboard keyboard, int x, int y, int width, int height, int speed) {
        this.keyboard = keyboard;
        this.rect = new Rectangle(new Point(x, y), width, height);
        this.color = game.Settings.getTheme().paddle();
        this.speed = speed;
    }

    public void moveLeft() {
        double y = rect.getUpLeft().getY();
        double x = (rect.getUpLeft().getX() - speed + WRAP_WIDTH) % WRAP_WIDTH;
        rect = new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight());
    }

    public void moveRight() {
        double y = rect.getUpLeft().getY();
        double x = (rect.getUpLeft().getX() + speed) % WRAP_WIDTH;
        rect = new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight());
    }

    public void timePassed() {
        if (keyboard.isPressed(Keyboard.LEFT_KEY)) moveLeft();
        if (keyboard.isPressed(Keyboard.RIGHT_KEY)) moveRight();
    }

    public void drawOn(Surface d) {
        int x = (int) rect.getUpLeft().getX();
        int y = (int) rect.getUpLeft().getY();
        int w = (int) rect.getWidth();
        int h = (int) rect.getHeight();

        d.setColor(color);
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);

        if (x + w > GameConfig.WIDTH - GameConfig.BORDER) {
            int x2 = x - WRAP_WIDTH;
            d.setColor(color);
            d.fillRectangle(x2, y, w, h);
            d.setColor(Color.BLACK);
            d.drawRectangle(x2, y, w, h);
        }
        if (x < GameConfig.BORDER) {
            int x2 = x + WRAP_WIDTH;
            d.setColor(color);
            d.fillRectangle(x2, y, w, h);
            d.setColor(Color.BLACK);
            d.drawRectangle(x2, y, w, h);
        }
    }

    public Rectangle getCollisionRectangle() { return rect; }

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double spd = Math.hypot(dx, dy);

        double left  = rect.getUpLeft().getX();
        double top   = rect.getUpLeft().getY();
        double right = left + rect.getWidth();
        double eps   = 0.0001;

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        if (Math.abs(x - left) < eps || Math.abs(x - right) < eps) {
            return new Velocity(-dx, dy);
        }

        if (Math.abs(y - top) < eps) {
            double regionWidth = rect.getWidth() / 5.0;
            double relativeX = x - left;
            int region = (int) (relativeX / regionWidth) + 1;
            switch (region) {
                case 1: return Velocity.fromAngleAndSpeed(150, spd);
                case 2: return Velocity.fromAngleAndSpeed(120, spd);
                case 3: return new Velocity(dx, -dy);
                case 4: return Velocity.fromAngleAndSpeed(60, spd);
                case 5: return Velocity.fromAngleAndSpeed(30, spd);
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
