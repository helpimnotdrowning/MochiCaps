package io.github.helpimnotdrowning.mochicaps;

public class Globals {
    private static boolean simulate;
    private static String name;
    private static boolean tweetOnStart;
    private static boolean noRecover;

    private Globals() {}

    public static void setSimulationMode() {
        Globals.simulate = true;
    }

    public static boolean isSimulating() {
        return simulate;
    }

    public static void setBotName(String name) {
        Globals.name = name;
    }

    public static String getBotName() {
        return name;
    }

    public static void doTweetOnStart() {
        Globals.tweetOnStart = true;
    }

    public static boolean shouldTweetOnStart() {
        return tweetOnStart;
    }

    public static void setDoNotRecover() {
        Globals.noRecover = true;
    }

    public static boolean shouldNotRecover() {
        return noRecover;
    }
}
