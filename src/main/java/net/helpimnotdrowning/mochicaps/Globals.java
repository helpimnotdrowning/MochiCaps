package net.helpimnotdrowning.mochicaps;

/**
 * A class to hold global values that are going to be accessed from lots of places (I hope)
 */
public class Globals {
    private static boolean simulate;
    private static String name;
    private static boolean tweetOnStart;
    private static boolean noRecover;

    private Globals() {}

    /**
     * Set simulation mode where things like saving state, writing files, creating posts, uploading images, etc, will
     * NOT happen. Images that *should* have been uploaded will instead be saved to disk, so this isn't a true read-only
     * mode.
     */
    public static void setSimulationMode() {
        Globals.simulate = true;
    }

    /**
     * Get simulation mode.
     * @return Whether the bot should be in simulation mode or not.
     */
    public static boolean isSimulating() {
        return simulate;
    }

    /**
     * Sets the target bot name.
     * MochiCaps can hold many bot configs, so the specific bot name is needed to know what bot you wish to start.
     * @param name Target bot name (to be launched)
     */
    public static void setBotName(String name) {
        //TODO: check if named bot actually exists
        Globals.name = name;
    }

    /**
     * Get the target bot name (to be launched).
     * @return The bot name
     */
    public static String getBotName() {
        return name;
    }

    /**
     * Tells the bot if it should tweet upon starting.
     */
    public static void setTweetOnStart() {
        Globals.tweetOnStart = true;
    }

    /**
     * Check if the bot should tweet upon starting up.
     * @return Whether the bot should tweet or not
     */
    public static boolean shouldTweetOnStart() {
        return tweetOnStart;
    }

    /**
     * Tell the bot to ignore and remove a crashfile.
     * The crashfile is created whenever the bot (you guessed it) crashes/exits unexpextedly so it can restart at the correct position and
     * not repeat posts.

     * This may NOT work when exiting via a keyboard interrupt (ctrl+c), quitting/closing the terminal window, or a
     * system/unexpected shutdown.
     */
    public static void setDoNotRecover() {
        Globals.noRecover = true;
    }

    public static boolean shouldNotRecover() {
        return noRecover;
    }
}
