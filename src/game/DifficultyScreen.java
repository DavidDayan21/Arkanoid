package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;

public class DifficultyScreen {
    private final GameWindow window;

    private static final int BTN_X = 260, BTN_W = 280, BTN_H = 90;
    private static final int EASY_Y   = 160;
    private static final int MEDIUM_Y = 270;
    private static final int HARD_Y   = 380;
    private static final int BACK_X = 25, BACK_Y = 20, BACK_W = 90, BACK_H = 35;

    public DifficultyScreen(GameWindow window) {
        this.window = window;
    }

    public Difficulty show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(25, 25, 35));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            // Back button
            d.setColor(new Color(60, 60, 60));
            d.fillRoundRect(BACK_X, BACK_Y, BACK_W, BACK_H, 8);
            d.setColor(Color.WHITE);
            d.drawText(BACK_X + 10, BACK_Y + 23, "← Back", 15);

            d.drawTextCentered(120, "SELECT DIFFICULTY", 28, Color.WHITE);

            // EASY
            d.setColor(new Color(40, 160, 70));
            d.fillRoundRect(BTN_X, EASY_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, EASY_Y + 5, BTN_W, 45, "EASY", 24, Color.WHITE);
            d.drawTextInRect(BTN_X, EASY_Y + 50, BTN_W, 35, "3 balls  ·  Wide paddle  ·  Speed 4", 13,
                    new Color(220, 220, 220));

            // MEDIUM
            d.setColor(new Color(200, 120, 20));
            d.fillRoundRect(BTN_X, MEDIUM_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, MEDIUM_Y + 5, BTN_W, 45, "MEDIUM", 24, Color.WHITE);
            d.drawTextInRect(BTN_X, MEDIUM_Y + 50, BTN_W, 35, "2 balls  ·  Normal paddle  ·  Speed 6", 13,
                    new Color(220, 220, 220));

            // HARD
            d.setColor(new Color(190, 40, 40));
            d.fillRoundRect(BTN_X, HARD_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, HARD_Y + 5, BTN_W, 45, "HARD", 24, Color.WHITE);
            d.drawTextInRect(BTN_X, HARD_Y + 50, BTN_W, 35, "1 ball   ·  Narrow paddle  ·  Speed 9", 13,
                    new Color(220, 220, 220));

            window.show(d);

            if (mouse.clickedInside(BACK_X, BACK_Y, BACK_W, BACK_H))    return null;
            if (mouse.clickedInside(BTN_X, EASY_Y, BTN_W, BTN_H))       return Difficulty.EASY;
            if (mouse.clickedInside(BTN_X, MEDIUM_Y, BTN_W, BTN_H))     return Difficulty.MEDIUM;
            if (mouse.clickedInside(BTN_X, HARD_Y, BTN_W, BTN_H))       return Difficulty.HARD;

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
