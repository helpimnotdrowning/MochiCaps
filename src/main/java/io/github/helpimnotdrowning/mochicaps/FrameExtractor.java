package io.github.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FrameExtractor {
    private static final Log LOGGER = new Log(FrameExtractor.class.getName());

    /**
     * Alternate entrypoint to just extract frames. Really the only reason this is here is because
     * <a href="github.com/helpimnotdrowning/tweet-screencap/blob/master/get_frame.py">I did it in python.</a>
     * @param args dont use wont do anything yet lol
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        EasyTime eTime;

        LOGGER.print("video path: ");
        Path path = Paths.get(stdin.nextLine());
        LOGGER.print("time (in seconds or as HH:MM:SS.ms) : ");
        String time = stdin.nextLine();

        if (time.matches("\\d\\d:\\d\\d:\\d\\d.\\d\\d")) {
            int hours = Integer.parseInt(time.charAt(0) + "" + time.charAt(1));
            int minutes = Integer.parseInt(time.charAt(3) + "" + time.charAt(4));
            int seconds = Integer.parseInt(time.charAt(6) + "" + time.charAt(7));
            int ms = Integer.parseInt(time.charAt(9) + "" + time.charAt(10));

            LOGGER.print(String.format("%s %s %s %s", hours, minutes, seconds, ms));

            eTime = new EasyTime(hours, minutes, seconds, ms);

            LOGGER.print(eTime.debugString());

            getFrame(eTime, path);

        } else {
            try {
                eTime = new EasyTime(Float.parseFloat(time));
                getFrame(eTime, path);
            } catch (NumberFormatException e) {
                //TODO: shutdown method to only shutdown the bot instance that called this method instead of everything
                LOGGER.error(String.format("Time of %s couldn't be parsed as float or as \"HH:MM:SS.ms\"!", time));
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void getFrame(EasyTime sec, Path path) {
        //TODO: auto fail if sec is greater than video length
        extract(sec, path);
    }

    public static void extract(EasyTime sec, Path path) {
        final Path tmpFileName = Paths.get("tmpFrameExtractor.png");

        try {
            new ProcessBuilder("ffmpeg", "-y", "-loglevel", "warning", "-ss", sec.getTime(), "-i",
                    path.toString(), "-vframes", "1", tmpFileName.toString()).start();
        } catch (IOException e) {
            LOGGER.error(String.format("Couldn't extract frame at path %s and time %s!", path.toString(),
                    sec.debugString()));
            e.printStackTrace();
            //TODO: shutdown method to only shutdown the bot instance that called this method instead of everything
            System.exit(1);
        }
    }

    private static void /*EasyTime*/ getVideoLength(Path path) throws IOException {
        Process ffprobe = new ProcessBuilder("ffprobe", "-v", "error", "-show_entries", "format=duration",
                "-of", "default=noprint_wrappers=1:nokey=1", path.toString()).start();
        //(float)ffprobe.inputReader();
    }
}
