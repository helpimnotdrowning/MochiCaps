package io.github.helpimnotdrowning.mochicaps;


public class Main {
    private static final Log LOGGER = new Log("Main");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        LOGGER.debug("Among");
        LOGGER.info("Us");
        LOGGER.warn("In Real Life");
        LOGGER.error("Test");
        //System.out.println(secondsToTime(8025));
        EasyTime time = new EasyTime(1,2,3,4);
    }
}