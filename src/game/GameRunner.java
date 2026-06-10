package game;

import engine.GameWindow;

public class GameRunner {
    private GameWindow window;

    public GameRunner(GameWindow window) {
        this.window = window;
    }

    public void run() {
        while (true) {
            String menuResult = new MainMenuScreen(window).show();
            if (menuResult.equals("quit")) {
                System.exit(0);
            }
            if (menuResult.equals("themes")) {
                new ThemeScreen(window).show();
                continue;
            }
            if (menuResult.equals("layouts")) {
                new LayoutScreen(window).show();
                continue;
            }
            if (menuResult.equals("fun")) {
                runFunGame();
                continue;
            }
            Difficulty difficulty = new DifficultyScreen(window).show();
            if (difficulty == null) continue;
            runClassicGame(difficulty);
        }
    }

    private void runFunGame() {
        while (true) {
            FunMode mode = new FunModeScreen(window).show();
            if (mode == null) return;
            runFunMode(mode);
        }
    }

    private void runFunMode(FunMode mode) {
        while (true) {
            FunGame game = new FunGame(window, mode);
            game.initialize();
            String result = game.run();
            if (result.equals("menu")) return;
            if (result.equals("restart")) continue;
            int finalScore = game.getFinalScore();
            String key = GameConfig.scoreKey(mode);
            int hs = HighScore.load(key);
            if (finalScore > hs) { HighScore.save(key, finalScore); hs = finalScore; }
            String modeName;
            switch (mode) {
                case MIRROR:       modeName = "Mirror";   break;
                case WIPER:        modeName = "Wiper";    break;
                case REVERSE:      modeName = "Reverse";  break;
                case REVERSE_PLUS: modeName = "Reverse+"; break;
                default:           modeName = "Fun";      break;
            }
            String endResult = new EndScreen(window).show(result.equals("win"), finalScore, hs, modeName);
            if (endResult.equals("menu")) return;
            if (endResult.equals("restart")) continue;
            return;
        }
    }

    private void runClassicGame(Difficulty difficulty) {
        while (true) {
            Game game = new Game(window, difficulty);
            game.initialize();
            String result = game.run();
            if (result.equals("menu")) return;
            if (result.equals("restart")) continue;
            int finalScore = game.getFinalScore();
            String key = GameConfig.scoreKey(difficulty);
            int hs = HighScore.load(key);
            if (finalScore > hs) { HighScore.save(key, finalScore); hs = finalScore; }
            String modeName = difficulty.name().charAt(0)
                    + difficulty.name().substring(1).toLowerCase();
            String endResult = new EndScreen(window).show(result.equals("win"), finalScore, hs, modeName);
            if (endResult.equals("menu")) return;
            if (endResult.equals("restart")) continue;
            return;
        }
    }
}
