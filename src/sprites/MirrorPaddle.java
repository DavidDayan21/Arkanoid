package sprites;

import engine.Keyboard;
import engine.Surface;
import game.Game;
import game.GameConfig;
import game.Sprite;

public class MirrorPaddle implements Sprite {
    private int rightX, leftX;
    private final int y = 560;
    private final int w = 80, h = 20;
    private final int speed = 7;
    private Keyboard keyboard;
    private CollidablePaddle leftPaddle, rightPaddle;

    public MirrorPaddle(Keyboard keyboard) {
        this.keyboard = keyboard;
        this.leftX  = GameConfig.BORDER + (GameConfig.WIDTH / 2 - GameConfig.BORDER - w) / 2;
        this.rightX = GameConfig.WIDTH / 2 + (GameConfig.WIDTH / 2 - GameConfig.BORDER - w) / 2;
        this.leftPaddle  = new CollidablePaddle(leftX,  y, w, h);
        this.rightPaddle = new CollidablePaddle(rightX, y, w, h);
    }

    public void timePassed() {
        boolean left  = keyboard.isPressed(engine.Keyboard.LEFT_KEY);
        boolean right = keyboard.isPressed(engine.Keyboard.RIGHT_KEY);
        int dx = 0;
        if (right) dx =  speed;
        if (left)  dx = -speed;
        if (dx == 0) return;

        int border  = GameConfig.BORDER;
        int centerX = GameConfig.WIDTH / 2;

        // Right paddle: on right half, bounded [centerX, WIDTH - BORDER - w]
        int newRightX = Math.max(centerX, Math.min(
                GameConfig.WIDTH - border - w, rightX + dx));

        // Left paddle: moves OPPOSITE, on left half, bounded [BORDER, centerX - w]
        int newLeftX = Math.max(border, Math.min(
                centerX - w, leftX - dx));

        rightX = newRightX;
        leftX  = newLeftX;
        leftPaddle.setX(leftX);
        rightPaddle.setX(rightX);
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
