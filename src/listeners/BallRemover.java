package listeners;

import game.Game;
import game.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * BallRemover is for  removing balls from the game they left the screen.
 * IT also keeping count of the number of remaining balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * BallRemover constructor.
     * @param game the game
     * @param remainingBalls counter for the number of balls left on the screen
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }


    /**
     * It is called when a ball hit the block death-region.
     * Then removes the ball from the game and updates the remaining balls counter.
     * @param beingHit the block being hit
     * @param hitter teh ball to remove because it hit dead-region
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
