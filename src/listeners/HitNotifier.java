package listeners;

/**
 * HitNotifier interface can be implemented by objects for notify other objects when they are hit.
 */
public interface HitNotifier {
    /**
     * Add a HitListener to listeners list that will be notified.
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a HitListener to listeners list that will be notified.
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
