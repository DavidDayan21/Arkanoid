package game;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import java.awt.Color;

public class LayoutBuilder {

    public static int build(Game game, BlockLayout layout, Theme theme) {
        switch (layout) {
            case CLASSIC:      return buildClassic(game, theme);
            case PYRAMID:      return buildPyramid(game, theme);
            case CHECKERBOARD: return buildCheckerboard(game, theme);
            case WALL:         return buildWall(game, theme);
            case BIG_BLOCKS:   return buildBigBlocks(game, theme);
            case DIAMOND:      return buildDiamond(game, theme);
            default:           return buildClassic(game, theme);
        }
    }

    private static Block addBlock(Game game, double x, double y,
                                   double w, double h, Color color,
                                   listeners.BlockRemover remover,
                                   listeners.ScoreTrackingListener scorer) {
        Block b = new Block(new Rectangle(new Point(x, y), w, h), color);
        b.addToGame(game);
        b.addHitListener(remover);
        b.addHitListener(scorer);
        return b;
    }

    private static int buildClassic(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        for (int r = 0; r < 6; r++) {
            for (int i = 0; i < 12 - r; i++) {
                addBlock(game, 180 + 50 * r + i * 50, 100 + r * 25, 50, 25,
                         col[r], remover, scorer);
                count++;
            }
        }
        return count;
    }

    private static int buildPyramid(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        int maxCols = 12;
        for (int r = 0; r < 6; r++) {
            int cols = maxCols - r * 2;
            if (cols <= 0) break;
            double startX = (GameConfig.WIDTH - cols * 50) / 2.0;
            for (int i = 0; i < cols; i++) {
                addBlock(game, startX + i * 50, 80 + r * 28, 50, 25,
                         col[r], remover, scorer);
                count++;
            }
        }
        return count;
    }

    private static int buildCheckerboard(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        int cols = 13, rows = 6;
        double startX = 55, startY = 80;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r + c) % 2 == 0) {
                    addBlock(game, startX + c * 55, startY + r * 30, 52, 26,
                             col[r % col.length], remover, scorer);
                    count++;
                }
            }
        }
        return count;
    }

    private static int buildWall(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        int cols = 13, rows = 6;
        double startX = 55, startY = 80;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                addBlock(game, startX + c * 55, startY + r * 30, 52, 26,
                         col[r % col.length], remover, scorer);
                count++;
            }
        }
        return count;
    }

    private static int buildBigBlocks(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        int cols = 7, rows = 5;
        double startX = 55, startY = 80;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                addBlock(game, startX + c * 100, startY + r * 52, 96, 48,
                         col[r % col.length], remover, scorer);
                count++;
            }
        }
        return count;
    }

    private static int buildDiamond(Game game, Theme theme) {
        Color[] col = theme.blockColors();
        listeners.BlockRemover remover = game.getRemover();
        listeners.ScoreTrackingListener scorer = game.getScorer();
        int count = 0;
        int[] widths = {2, 4, 6, 8, 10, 8, 6, 4, 2};
        for (int r = 0; r < widths.length; r++) {
            int cols = widths[r];
            double startX = (GameConfig.WIDTH - cols * 50) / 2.0;
            for (int c = 0; c < cols; c++) {
                addBlock(game, startX + c * 50, 60 + r * 26, 48, 23,
                         col[r % col.length], remover, scorer);
                count++;
            }
        }
        return count;
    }
}
