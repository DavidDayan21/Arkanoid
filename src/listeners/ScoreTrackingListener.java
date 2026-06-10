package listeners;

import game.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * ScoreTrackingListener updates the score when blocks are hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * The ScoreTrackingListener constructor.
     * @param scoreCounter the counter holding the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Removes the ball from the game and updates the remaining balls counter.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
