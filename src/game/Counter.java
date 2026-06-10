package game;

/**
 * Counter is a simple class for counting things.
 */
public class Counter {
    private int value;

    /**
     * Constructs a counter with an initial value.
     * @param initial the initial value.
     */
    public Counter(int initial) {
        this.value = initial;
    }

    /**
     * Adds number to current count.
     * @param number the number to add.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Subtracts number from current count.
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets the current count.
     * @return the current value.
     */
    public int getValue() {
        return this.value;
    }

    public void setValue(int v) {
        this.value = v;
    }
}
