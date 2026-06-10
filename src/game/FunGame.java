package game;

import engine.GameWindow;
import engine.Surface;
import engine.Keyboard;
import engine.Mouse;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import sprites.Ball;
import sprites.Block;
import sprites.MirrorPaddle;
import sprites.WiperPaddle;
import sprites.ReversePaddle;
import java.awt.Color;

public class FunGame extends Game {
    private FunMode mode;

    public FunGame(GameWindow window, FunMode mode) {
        super(window, Difficulty.MEDIUM);
        this.mode = mode;
    }

    @Override
    public void initialize() {
        switch (mode) {
            case MIRROR:
                new MirrorPaddle(window.getKeyboard()).addToGame(this);
                break;
            case WIPER:
                new WiperPaddle(window.getKeyboard()).addToGame(this);
                break;
            case REVERSE:
                new ReversePaddle(window.getKeyboard(), false, 100, 7).addToGame(this);
                break;
            case REVERSE_PLUS:
                new ReversePaddle(window.getKeyboard(), true, 65, 9).addToGame(this);
                break;
        }

        int[] startX;
        double[] angles;
        double spd;
        int ballCount;
        if (mode == FunMode.REVERSE_PLUS) {
            startX = new int[]{400};
            angles = new double[]{90.0};
            spd = 9.0;
            ballCount = 1;
        } else {
            startX = new int[]{370, 430};
            angles = new double[]{140.0, 40.0};
            spd = 6.0;
            ballCount = 2;
        }
        this.remainingBalls = new Counter(ballCount);

        for (int i = 0; i < ballCount; i++) {
            Ball ball = new Ball(startX[i], 555, 5, Settings.getTheme().ball());
            ball.setVelocity(Velocity.fromAngleAndSpeed(angles[i], spd));
            ball.setGameEnvironment(this.environment);
            this.addSprite(ball);
        }

        this.score = new Counter(0);
        this.remainingBlocks = new Counter(999);
        this.remover = new listeners.BlockRemover(this, this.remainingBlocks);
        this.scorer  = new listeners.ScoreTrackingListener(this.score);
        int built = LayoutBuilder.build(this, Settings.getLayout(), Settings.getTheme());
        this.remainingBlocks.setValue(built);

        Color borderColor = Settings.getTheme().border();
        Block top   = new Block(new Rectangle(new Point(0, GameConfig.SCORE_BAR),
                GameConfig.WIDTH, GameConfig.BORDER), borderColor);
        Block left  = new Block(new Rectangle(new Point(0, GameConfig.SCORE_BAR),
                GameConfig.BORDER, GameConfig.HEIGHT - GameConfig.SCORE_BAR), borderColor);
        Block right = new Block(new Rectangle(
                new Point(GameConfig.WIDTH - GameConfig.BORDER, GameConfig.SCORE_BAR),
                GameConfig.BORDER, GameConfig.HEIGHT - GameConfig.SCORE_BAR), borderColor);

        top.setBorder();
        left.setBorder();
        right.setBorder();
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);

        sprites.ScoreIndicator indicator = new sprites.ScoreIndicator(this.score);
        indicator.addToGame(this);

        Block deathRegion = new Block(
                new Rectangle(new Point(0, GameConfig.HEIGHT), GameConfig.WIDTH, 10), Color.BLACK);
        deathRegion.addToGame(this);
        deathRegion.setAsDeathRegion();

        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.addHitListener(ballRemover);
    }

    @Override
    public String run() {
        int frameTime = 1000 / GameConfig.FPS;
        Keyboard keyboard = window.getKeyboard();
        Mouse mouse = window.getMouse();
        boolean wasP = keyboard.isPressed("p");

        final int HB_X = 22, HB_Y = 44, HB_W = 28, HB_H = 24;

        while (true) {
            long startTime = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(Settings.getTheme().background());
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
            this.sprites.drawAllOn(d);

            d.setColor(new Color(50, 50, 60));
            d.fillRoundRect(22, 44, 28, 24, 6);
            d.setColor(Color.WHITE);
            d.fillRectangle(27, 49, 18, 3);
            d.fillRectangle(27, 54, 18, 3);
            d.fillRectangle(27, 59, 18, 3);

            window.show(d);
            this.sprites.notifyAllTimePassed();

            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
                return "win";
            }
            if (this.remainingBalls.getValue() == 0) {
                return "lose";
            }

            if (mouse.clickedInside(HB_X, HB_Y, HB_W, HB_H)) {
                String pauseResult = new PauseMenuScreen(window).show();
                if (pauseResult.equals("menu"))    return "menu";
                if (pauseResult.equals("restart")) return "restart";
            }

            boolean nowP = keyboard.isPressed("p");
            if (nowP && !wasP) {
                String pauseResult = new PauseMenuScreen(window).show();
                wasP = keyboard.isPressed("p");
                if (pauseResult.equals("menu"))    return "menu";
                if (pauseResult.equals("restart")) return "restart";
            } else {
                wasP = nowP;
            }

            long used = System.currentTimeMillis() - startTime;
            long sleep = frameTime - used;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
