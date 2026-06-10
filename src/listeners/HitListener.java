package listeners;

import sprites.Block;
import sprites.Ball;

/**
 * HitListener interface can be implemented by any class for being notified when a ball hit block.
 */
public interface HitListener {
    /**
     * HitListener called whenever a block object is hit.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
