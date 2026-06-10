package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;

public class PauseMenuScreen {
    private final GameWindow window;

    private static final int OVL_W = 300, OVL_H = 280;
    private static final int OVL_X = (GameConfig.WIDTH - OVL_W) / 2;
    private static final int OVL_Y = (GameConfig.HEIGHT - OVL_H) / 2;

    private static final int BTN_W = 200, BTN_H = 52;
    private static final int BTN_X = (GameConfig.WIDTH - BTN_W) / 2;

    private static final int RESUME_Y    = OVL_Y + 90;
    private static final int RESTART_Y   = OVL_Y + 155;
    private static final int MAINMENU_Y  = OVL_Y + 220;

    public PauseMenuScreen(GameWindow window) {
        this.window = window;
    }

    public String show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;
        java.awt.image.BufferedImage frozenFrame = window.getLastFrame();

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            // Draw frozen game frame as background
            d.getGraphics().drawImage(frozenFrame, 0, 0, null);
            // Semi-transparent dark overlay for readability
            d.getGraphics().setComposite(
                java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.55f));
            d.setColor(new Color(0, 0, 0));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
            d.getGraphics().setComposite(
                java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

            d.setColor(new Color(35, 35, 45));
            d.fillRoundRect(OVL_X, OVL_Y, OVL_W, OVL_H, 15);

            d.drawTextCentered(OVL_Y + 55, "PAUSED", 32, Color.WHITE);

            d.setColor(new Color(40, 160, 70));
            d.fillRoundRect(BTN_X, RESUME_Y, BTN_W, BTN_H, 10);
            d.drawTextInRect(BTN_X, RESUME_Y, BTN_W, BTN_H, "RESUME", 18, Color.WHITE);

            d.setColor(new Color(200, 120, 20));
            d.fillRoundRect(BTN_X, RESTART_Y, BTN_W, BTN_H, 10);
            d.drawTextInRect(BTN_X, RESTART_Y, BTN_W, BTN_H, "RESTART", 18, Color.WHITE);

            d.setColor(new Color(90, 90, 100));
            d.fillRoundRect(BTN_X, MAINMENU_Y, BTN_W, BTN_H, 10);
            d.drawTextInRect(BTN_X, MAINMENU_Y, BTN_W, BTN_H, "MAIN MENU", 18, Color.WHITE);

            window.show(d);

            if (mouse.clickedInside(BTN_X, RESUME_Y,   BTN_W, BTN_H)) return "resume";
            if (mouse.clickedInside(BTN_X, RESTART_Y,  BTN_W, BTN_H)) return "restart";
            if (mouse.clickedInside(BTN_X, MAINMENU_Y, BTN_W, BTN_H)) return "menu";

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }
}
