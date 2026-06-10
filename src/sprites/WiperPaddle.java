package sprites;

import engine.Keyboard;
import engine.Surface;
import game.Game;
import game.GameConfig;
import game.Sprite;

public class WiperPaddle implements Sprite {
    private int leftX;
    private final int gap = 300;
    private final int y = 560;
    private final int w = 80, h = 20;
    private final int speed = 7;
    private Keyboard keyboard;
    private CollidablePaddle leftPaddle, rightPaddle;

    public WiperPaddle(Keyboard keyboard) {
        this.keyboard = keyboard;
        this.leftX = 210;
        this.leftPaddle  = new CollidablePaddle(leftX,        y, w, h);
        this.rightPaddle = new CollidablePaddle(leftX + gap,  y, w, h);
    }

    public void timePassed() {
        boolean left  = keyboard.isPressed(engine.Keyboard.LEFT_KEY);
        boolean right = keyboard.isPressed(engine.Keyboard.RIGHT_KEY);
        int dx = 0;
        if (right) dx = speed;
        if (left)  dx = -speed;

        if (dx != 0) {
            int minX = GameConfig.BORDER;
            int maxX = GameConfig.WIDTH - GameConfig.BORDER - gap - w;
            leftX = Math.max(minX, Math.min(maxX, leftX + dx));
            leftPaddle.setX(leftX);
            rightPaddle.setX(leftX + gap);
        }
    }

    public void drawOn(Surface d) {
        leftPaddle.drawOn(d);
        rightPaddle.drawOn(d);
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(leftPaddle);
        g.addCollidable(rightPaddle);
    }

    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(leftPaddle);
        g.removeCollidable(rightPaddle);
    }
}
