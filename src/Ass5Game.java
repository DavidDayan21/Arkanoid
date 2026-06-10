import engine.GameWindow;
import game.GameConfig;
import game.GameRunner;

public class Ass5Game {
    public static void main(String[] args) {
        GameWindow window = new GameWindow("Arkanoid",
                                           GameConfig.WIDTH,
                                           GameConfig.HEIGHT);
        new GameRunner(window).run();
    }
}
