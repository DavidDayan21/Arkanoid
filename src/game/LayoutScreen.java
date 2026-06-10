package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;
public class LayoutScreen {
    private final GameWindow window;

    private static final int CARD_W = 180, CARD_H = 110;
    private static final int NAME_STRIP_H = 20;
    private static final int PREVIEW_H = CARD_H - NAME_STRIP_H;
    private static final int[] COL_X = {60, 310, 560};
    private static final int[] ROW_Y = {120, 255};

    private static final int BACK_X = 25, BACK_Y = 20, BACK_W = 90, BACK_H = 35;

    public LayoutScreen(GameWindow window) {
        this.window = window;
    }

    public void show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;
        BlockLayout[] layouts = BlockLayout.values();

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(20, 20, 30));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            // Back button
            d.setColor(new Color(60, 60, 80));
            d.fillRoundRect(BACK_X, BACK_Y, BACK_W, BACK_H, 8);
            d.drawTextInRect(BACK_X, BACK_Y, BACK_W, BACK_H, "← Back", 14, Color.WHITE);

            // Title
            d.drawTextCentered(95, "LAYOUTS", 26, Color.WHITE);

            // Cards
            for (int i = 0; i < layouts.length; i++) {
                int col = i % 3;
                int row = i / 3;
                int cx = COL_X[col];
                int cy = ROW_Y[row];
                BlockLayout layout = layouts[i];
                boolean selected = (layout == Settings.getLayout());
                drawLayoutCard(d, cx, cy, layout, selected);
            }

            window.show(d);

            // Back click
            if (mouse.clickedInside(BACK_X, BACK_Y, BACK_W, BACK_H)) return;

            // Card clicks
            for (int i = 0; i < layouts.length; i++) {
                int col = i % 3;
                int row = i / 3;
                int cx = COL_X[col];
                int cy = ROW_Y[row];
                if (mouse.clickedInside(cx, cy, CARD_W, CARD_H)) {
                    Settings.setLayout(layouts[i]);
                }
            }

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }

    private void drawLayoutCard(Surface d, int cardX, int cardY, BlockLayout layout, boolean selected) {
        Color[] cols = Settings.getTheme().blockColors();

        // Background
        d.setColor(Settings.getTheme().background());
        d.fillRectangle(cardX, cardY, CARD_W, PREVIEW_H);

        switch (layout) {
            case CLASSIC: {
                int bw = 14, bh = 7, gap = 2;
                int maxCols = 7;
                int totalW = maxCols * (bw + gap) - gap;
                int startX = cardX + (CARD_W - totalW) / 2;
                int startY = cardY + 10;
                for (int r = 0; r < 4; r++) {
                    int count = 7 - r;
                    for (int c = 0; c < count; c++) {
                        d.setColor(cols[r % cols.length]);
                        d.fillRectangle(startX + r * (bw + gap) / 2 + c * (bw + gap),
                                        startY + r * (bh + gap), bw, bh);
                    }
                }
                break;
            }
            case PYRAMID: {
                int pbw = 14, pbh = 7, pgap = 2;
                int[] pcounts = {8, 6, 4, 2};
                for (int r = 0; r < 4; r++) {
                    int cnt = pcounts[r];
                    int rowW = cnt * (pbw + pgap) - pgap;
                    int sx = cardX + (CARD_W - rowW) / 2;
                    int sy = cardY + 8 + r * (pbh + pgap);
                    for (int c = 0; c < cnt; c++) {
                        d.setColor(cols[r % cols.length]);
                        d.fillRectangle(sx + c * (pbw + pgap), sy, pbw, pbh);
                    }
                }
                break;
            }
            case CHECKERBOARD: {
                int cbw = 14, cbh = 8, cgap = 3;
                int ccols = 6, crows = 4;
                int cgridW = ccols * (cbw + cgap) - cgap;
                int cgridH = crows * (cbh + cgap) - cgap;
                int csx = cardX + (CARD_W - cgridW) / 2;
                int csy = cardY + (PREVIEW_H - NAME_STRIP_H - cgridH) / 2 + 4;
                for (int r = 0; r < crows; r++) {
                    for (int c = 0; c < ccols; c++) {
                        if ((r + c) % 2 == 0) {
                            d.setColor(cols[r % cols.length]);
                            d.fillRectangle(csx + c * (cbw + cgap), csy + r * (cbh + cgap), cbw, cbh);
                        }
                    }
                }
                break;
            }
            case WALL: {
                int wbw = 14, wbh = 8, wgap = 3;
                int wcols = 6, wrows = 4;
                int wgridW = wcols * (wbw + wgap) - wgap;
                int wgridH = wrows * (wbh + wgap) - wgap;
                int wsx = cardX + (CARD_W - wgridW) / 2;
                int wsy = cardY + (PREVIEW_H - NAME_STRIP_H - wgridH) / 2 + 4;
                for (int r = 0; r < wrows; r++) {
                    for (int c = 0; c < wcols; c++) {
                        d.setColor(cols[r % cols.length]);
                        d.fillRectangle(wsx + c * (wbw + wgap), wsy + r * (wbh + wgap), wbw, wbh);
                    }
                }
                break;
            }
            case BIG_BLOCKS: {
                int bbw = 42, bbh = 22, bbgap = 5;
                int bbcols = 3, bbrows = 2;
                int bbgridW = bbcols * (bbw + bbgap) - bbgap;
                int bbgridH = bbrows * (bbh + bbgap) - bbgap;
                int bbsx = cardX + (CARD_W - bbgridW) / 2;
                int bbsy = cardY + (PREVIEW_H - NAME_STRIP_H - bbgridH) / 2 + 4;
                for (int r = 0; r < bbrows; r++) {
                    for (int c = 0; c < bbcols; c++) {
                        d.setColor(cols[(r * bbcols + c) % cols.length]);
                        d.fillRectangle(bbsx + c * (bbw + bbgap), bbsy + r * (bbh + bbgap), bbw, bbh);
                    }
                }
                break;
            }
            case DIAMOND: {
                int dbw = 14, dbh = 7, dgap = 3;
                int[] dcounts = {2, 4, 6, 4, 2};
                int dtotalH = dcounts.length * (dbh + dgap) - dgap;
                int dsy = cardY + (PREVIEW_H - NAME_STRIP_H - dtotalH) / 2 + 4;
                for (int r = 0; r < dcounts.length; r++) {
                    int cnt = dcounts[r];
                    int rowW = cnt * (dbw + dgap) - dgap;
                    int dsx = cardX + (CARD_W - rowW) / 2;
                    for (int c = 0; c < cnt; c++) {
                        d.setColor(cols[r % cols.length]);
                        d.fillRectangle(dsx + c * (dbw + dgap), dsy + r * (dbh + dgap), dbw, dbh);
                    }
                }
                break;
            }
        }

        // Border
        d.setColor(selected ? new Color(255, 220, 0) : new Color(60, 60, 70));
        d.drawRectangle(cardX, cardY, CARD_W, CARD_H);

        // Name strip
        d.drawTextInRect(cardX, cardY + CARD_H - NAME_STRIP_H,
                         CARD_W, NAME_STRIP_H, layout.displayName(), 12, Color.WHITE);
    }
}
