package io.github.helpimnotdrowning.mochicaps;

/**
 * Interface that provides a way to print objects on a whim without even thinking about it.
 */
public interface IBetterRepresentation {
    /**
     * Friendly string representation of object.
     */
    public String friendlyString();

    /**
     * String representation of object with some more information.
     * <br>
     * May return {@link #friendlyString()} when there isn't any more information to show.
     */
    default public String infoString() {
        return friendlyString();
    };

    /**
     * String representation of object with the most possible information.
     * <br>
     * May return {@link #infoString()} when there isn't any more information to show.
     */
    default public String debugString() {
        return infoString();
    };
}
