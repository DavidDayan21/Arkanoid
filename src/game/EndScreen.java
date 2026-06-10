package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;

public class EndScreen {
    private final GameWindow window;

    private static final int BTN_W = 190, BTN_H = 58;
    private static final int BTN_Y = 350;
    private static final int PLAY_X = 170;
    private static final int MENU_X = 440;

    public EndScreen(GameWindow window) {
        this.window = window;
    }

    public String show(boolean won, int score, int highScore, String modeName) {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;

        boolean isNewHighScore = score > highScore;
        int displayHighScore = isNewHighScore ? score : highScore;

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(25, 25, 35));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            if (won) {
                d.drawTextCentered(180, "YOU WIN!", 52, new Color(255, 220, 50));
            } else {
                d.drawTextCentered(180, "GAME OVER", 52, new Color(210, 50, 50));
            }

            d.drawTextCentered(250, "Score: " + score, 30, Color.WHITE);

            if (isNewHighScore) {
                d.drawTextCentered(295, "★  NEW HIGH SCORE!  ★", 20, new Color(50, 220, 220));
            } else {
                d.drawTextCentered(295, "Best " + modeName + ": " + displayHighScore, 18, new Color(140, 140, 140));
            }

            d.setColor(new Color(40, 160, 70));
            d.fillRoundRect(PLAY_X, BTN_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(PLAY_X, BTN_Y, BTN_W, BTN_H, "PLAY AGAIN", 19, Color.WHITE);

            d.setColor(new Color(90, 90, 100));
            d.fillRoundRect(MENU_X, BTN_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(MENU_X, BTN_Y, BTN_W, BTN_H, "MAIN MENU", 19, Color.WHITE);

            window.show(d);

            if (mouse.clickedInside(PLAY_X, BTN_Y, BTN_W, BTN_H)) return "restart";
            if (mouse.clickedInside(MENU_X, BTN_Y, BTN_W, BTN_H)) return "menu";

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
