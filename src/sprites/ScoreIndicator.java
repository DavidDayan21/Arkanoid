package sprites;

import game.Counter;
import game.Game;
import game.Sprite;
import engine.Surface;

public class ScoreIndicator implements Sprite {
    private Counter score;

    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    public void drawOn(Surface d) {
        d.setColor(game.Settings.getTheme().scoreBar());
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(game.Settings.getTheme().scoreText());
        d.drawText(330, 15, "Score: " + this.score.getValue(), 15);
    }

    public void timePassed() {}

    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
