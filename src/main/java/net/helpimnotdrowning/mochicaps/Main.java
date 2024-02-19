package net.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        parseArgs(args);
    }

    private static void parseArgs(String[] args) {
        Log LOGGER = new Log("Main/parseArgs");

        LOGGER.print("CLI ARGS : " + Arrays.toString(args));

        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("-H") /*|| true*/) {
                LOGGER.print("""
                        Unrecognized arguments are IGNORED.
                            
                        Usage:
                          --help
                          -H
                              ...what do you think this does?
                            
                          --name=<bot name>
                          -N=<bot name>
                              [Required] Starts the named bot
                            
                          --tweet_now
                              Tweets on bot start, requires CLI input
                              "y" to confirm, anything else means no (start normally)
                          
                          --simulate
                          -S
                              Start in simulation mode (won't save state, delete/edit files,
                                upload images, send posts, etc.)
                              Will save attempted image uploads to disk
                              
                          --no-recover
                          -N
                              Do not use the recovery file for some reason
                              The recovery file is written on a crash so the bot knows to skip ahead
                                and not post repeat screencaps. Why you would want to disable this is
                                is beyond me, but ¯\\_ (ツ)_/¯
                         """);
                System.exit(0);
            }

            if (arg.equals("--simulate") || arg.equals("-S")) {
                Globals.setSimulationMode();
            }

            if (arg.equals("--tweet_now")) {
                Globals.setTweetOnStart();
            }

            if (arg.equals("--no-recover") || arg.equals("-N")) {
                Globals.setDoNotRecover();
            }
        }

        String ck ;
        String cs ;
        String at ;
        String ats;


        TwitterAPI(ck, cs, at, ats);

        Utils.crash("Message", new IOException("dddd"));
    }
}
