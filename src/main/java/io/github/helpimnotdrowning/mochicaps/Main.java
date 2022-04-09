package io.github.helpimnotdrowning.mochicaps;


public class Main {
    private static final Log LOGGER = new Log(Main.class);

    public static void main(String[] args) {
        LOGGER.print("Hello World!");
        LOGGER.debug("Among");
        LOGGER.info("Us");
        LOGGER.warn("In Real Life");
        LOGGER.error("Test");
        //System.out.println(secondsToTime(8025));
        EasyTime time = new EasyTime(1,2,3,4);
        LOGGER.print(time.debugString());
    }
}