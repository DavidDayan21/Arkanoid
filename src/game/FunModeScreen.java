package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;

public class FunModeScreen {
    private final GameWindow window;

    private static final int BTN_X = 260, BTN_W = 280, BTN_H = 75;
    private static final int MIRROR_Y          = 165;
    private static final int WIPER_Y           = 255;
    private static final int REVERSE_Y         = 345;
    private static final int REVERSE_PLUS_Y = 435;
    private static final int BACK_X = 25, BACK_Y = 20, BACK_W = 90, BACK_H = 35;

    public FunModeScreen(GameWindow window) {
        this.window = window;
    }

    public FunMode show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(25, 25, 35));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            d.setColor(new Color(60, 60, 60));
            d.fillRoundRect(BACK_X, BACK_Y, BACK_W, BACK_H, 8);
            d.setColor(Color.WHITE);
            d.drawText(BACK_X + 10, BACK_Y + 23, "← BACK", 15);

            d.drawTextCentered(100, "FUN MODES", 32, Color.YELLOW);
            d.drawTextCentered(135, "Select a game mode", 16, new Color(160, 160, 160));

            // MIRROR
            d.setColor(new Color(70, 50, 180));
            d.fillRoundRect(BTN_X, MIRROR_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, MIRROR_Y, BTN_W, BTN_H - 27, "MIRROR", 22, Color.WHITE);
            d.drawTextCentered(MIRROR_Y + 48, "Two mirrored paddles — opposites attract", 13, new Color(210, 210, 210));

            // WIPER
            d.setColor(new Color(50, 150, 80));
            d.fillRoundRect(BTN_X, WIPER_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, WIPER_Y, BTN_W, BTN_H - 27, "WIPER", 22, Color.WHITE);
            d.drawTextCentered(WIPER_Y + 48, "Two paddles, one unit — sweep together", 13, new Color(210, 210, 210));

            // REVERSE
            d.setColor(new Color(180, 100, 20));
            d.fillRoundRect(BTN_X, REVERSE_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, REVERSE_Y, BTN_W, BTN_H - 27, "REVERSE", 22, Color.WHITE);
            d.drawTextCentered(REVERSE_Y + 48, "Controls flipped — left is right", 13, new Color(210, 210, 210));

            // REVERSE+
            d.setColor(new Color(160, 40, 120));
            d.fillRoundRect(BTN_X, REVERSE_PLUS_Y, BTN_W, BTN_H, 12);
            d.drawTextInRect(BTN_X, REVERSE_PLUS_Y, BTN_W, BTN_H - 27, "REVERSE+", 22, Color.WHITE);
            d.drawTextCentered(REVERSE_PLUS_Y + 48, "Controls flipped + physics inverted", 13, new Color(210, 210, 210));

            window.show(d);

            if (mouse.clickedInside(BACK_X, BACK_Y, BACK_W, BACK_H))                 return null;
            if (mouse.clickedInside(BTN_X, MIRROR_Y, BTN_W, BTN_H))                  return FunMode.MIRROR;
            if (mouse.clickedInside(BTN_X, WIPER_Y, BTN_W, BTN_H))                   return FunMode.WIPER;
            if (mouse.clickedInside(BTN_X, REVERSE_Y, BTN_W, BTN_H))                 return FunMode.REVERSE;
            if (mouse.clickedInside(BTN_X, REVERSE_PLUS_Y, BTN_W, BTN_H))            return FunMode.REVERSE_PLUS;

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
