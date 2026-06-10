package game;

public class GameConfig {
    public static final int WIDTH     = 800;
    public static final int HEIGHT    = 600;
    public static final int BORDER    = 20;
    public static final int SCORE_BAR = 20;
    public static final int FPS       = 60;

    public static int ballCount(Difficulty d) {
        switch (d) {
            case EASY:   return 3;
            case MEDIUM: return 2;
            case HARD:   return 1;
            default:     return 2;
        }
    }

    public static double ballSpeed(Difficulty d) {
        switch (d) {
            case EASY:   return 4.0;
            case MEDIUM: return 6.0;
            case HARD:   return 9.0;
            default:     return 6.0;
        }
    }

    public static int paddleWidth(Difficulty d) {
        switch (d) {
            case EASY:   return 140;
            case MEDIUM: return 100;
            case HARD:   return 65;
            default:     return 100;
        }
    }

    public static int paddleSpeed(Difficulty d) {
        switch (d) {
            case EASY:   return 5;
            case MEDIUM: return 7;
            case HARD:   return 9;
            default:     return 7;
        }
    }

    public static String scoreKey(Difficulty d) {
        switch (d) {
            case EASY:   return "classic_easy";
            case MEDIUM: return "classic_medium";
            case HARD:   return "classic_hard";
            default:     return "classic_medium";
        }
    }

    public static String scoreKey(FunMode m) {
        switch (m) {
            case MIRROR:        return "fun_mirror";
            case WIPER:         return "fun_wiper";
            case REVERSE:       return "fun_reverse";
            case REVERSE_PLUS:  return "fun_reverse_plus";
            default:            return "fun_unknown";
        }
    }
}
