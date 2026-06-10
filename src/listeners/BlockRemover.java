package listeners;

import game.Game;
import game.Counter;
import sprites.Block;
import sprites.Ball;

/**
 * BlockRemover class is in charge of removing blocks from the game.
 * It also keeping count of the number of remaining blocks.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Block remover constructor.
     * @param game the game
     * @param remainingBlocks the counter for remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * It is called when a ball hit a block.
     * Then removes the block from the game and updates the remaining blocks counter.
     * Remove also the listener of the removed block.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
