package io.github.helpimnotdrowning.mochicaps;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        parseArgs(args);
    }

    private static void parseArgs(String[] args) {
        Log LOGGER = new Log("Main/parseArgs");

        List<String> argsList = Arrays.asList(args);

        LOGGER.print("CLI ARGS : " + Arrays.toString(args));

        for (String arg : argsList) {
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

            if (arg.startsWith("--name=") || arg.startsWith("-N=")) {
                // split the arg on the "=" sign and take the last elements, which /should/ be the bot name
                // if there's an = sign in your bot name that's on you
                String[] botNameArg = arg.split("=");
                String botName = botNameArg[botNameArg.length-1];

                Globals.setBotName(botName);
            }

            if (arg.equals("--tweet_now")) {
                Globals.doTweetOnStart();
            }

            if (arg.equals("--no-recover") || arg.equals("-N")) {
                Globals.setDoNotRecover();
            }
        }
    }
}
