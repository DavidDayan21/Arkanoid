package game;
import engine.Surface;

public interface Sprite {
    void drawOn(Surface d);
    void timePassed();
}
