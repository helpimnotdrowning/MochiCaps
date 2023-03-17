package net.helpimnotdrowning.mochicaps;

public class Globals {
    private static boolean simulate;
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
     * TODO: REWORD COMMENT, THIS SEEMS CONFUSING!!!!
     * Tell the bot to ignore and remove a crash flag.
     * The crash flag is created whenever the bot crashes/exits crash flag, so it can restart at the correct position
     * and avoid repeating posts.

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
