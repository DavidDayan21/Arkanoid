package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;

public class MainMenuScreen {
    private final GameWindow window;

    private static final int CLASSIC_X = 160, CLASSIC_Y = 260, CARD_W = 200, CARD_H = 130;
    private static final int FUN_X = 440,     FUN_Y = 260;

    private static final int QUIT_X = GameConfig.WIDTH - 110;
    private static final int QUIT_Y = GameConfig.HEIGHT - 60;
    private static final int QUIT_W = 100, QUIT_H = 38;

    private static final int THEMES_X  = 25,  THEMES_Y  = 25, THEMES_W  = 110, THEMES_H  = 38;
    private static final int LAYOUTS_X = 145, LAYOUTS_Y = 25, LAYOUTS_W = 110, LAYOUTS_H = 38;

    public MainMenuScreen(GameWindow window) {
        this.window = window;
    }

    public String show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(25, 25, 35));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            d.drawTextCentered(130, "ARKANOID", 55, Color.WHITE);
            d.drawTextCentered(170, "Select your mode", 18, new Color(160, 160, 160));

            // THEMES button
            d.setColor(new Color(60, 50, 90));
            d.fillRoundRect(THEMES_X, THEMES_Y, THEMES_W, THEMES_H, 8);
            d.drawTextInRect(THEMES_X, THEMES_Y, THEMES_W, THEMES_H, "THEMES", 15, Color.WHITE);

            // LAYOUTS button
            d.setColor(new Color(40, 70, 60));
            d.fillRoundRect(LAYOUTS_X, LAYOUTS_Y, LAYOUTS_W, LAYOUTS_H, 8);
            d.drawTextInRect(LAYOUTS_X, LAYOUTS_Y, LAYOUTS_W, LAYOUTS_H, "LAYOUTS", 15, Color.WHITE);

            // CLASSIC card
            d.setColor(new Color(45, 110, 190));
            d.fillRoundRect(CLASSIC_X, CLASSIC_Y, CARD_W, CARD_H, 15);
            d.drawTextInRect(CLASSIC_X, CLASSIC_Y + 25, CARD_W, 45, "CLASSIC", 26, Color.WHITE);
            d.drawTextInRect(CLASSIC_X, CLASSIC_Y + 72, CARD_W, 28, "3 levels of difficulty", 13, new Color(200, 200, 200));

            // FUN card
            d.setColor(new Color(60, 40, 120));
            d.fillRoundRect(FUN_X, FUN_Y, CARD_W, CARD_H, 15);
            d.drawTextInRect(FUN_X, FUN_Y + 25, CARD_W, 45, "MODES", 26, Color.WHITE);
            d.drawTextInRect(FUN_X, FUN_Y + 72, CARD_W, 28, "4 game modes", 13, new Color(200, 190, 230));

            // QUIT button
            d.setColor(new Color(170, 45, 45));
            d.fillRoundRect(QUIT_X, QUIT_Y, QUIT_W, QUIT_H, 8);
            d.drawTextInRect(QUIT_X, QUIT_Y, QUIT_W, QUIT_H, "QUIT", 15, Color.WHITE);

            window.show(d);

            if (mouse.clickedInside(THEMES_X,  THEMES_Y,  THEMES_W,  THEMES_H))  return "themes";
            if (mouse.clickedInside(LAYOUTS_X, LAYOUTS_Y, LAYOUTS_W, LAYOUTS_H)) return "layouts";
            if (mouse.clickedInside(CLASSIC_X, CLASSIC_Y, CARD_W, CARD_H))       return "classic";
            if (mouse.clickedInside(FUN_X,     FUN_Y,     CARD_W, CARD_H))       return "fun";
            if (mouse.clickedInside(QUIT_X,    QUIT_Y,    QUIT_W, QUIT_H))       return "quit";

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
