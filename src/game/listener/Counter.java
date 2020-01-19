package game.listener;

/**
 * Counter Class.
 *
 * @author Tomer Ovadia
 */
public class Counter {
    private int value;

    /**
     * Constructor.
     *
     * @param val starting cout from
     */
    public Counter(int val) {
        this.value = val;
    }

    /**
     * add number to current count.
     *
     * @param number to add
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number to subtract
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets value.
     *
     * @return current value of counter.
     */
    public int getValue() {
        return this.value;
    }
}