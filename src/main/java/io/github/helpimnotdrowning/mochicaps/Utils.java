package io.github.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // https://stackoverflow.com/a/9375063
    public static String fixLineEndings(String DOSText) {
        return DOSText.replaceAll("\r\n", "\n");
    }

    public static String readFile(Path file) throws IOException {
        String contents = Files.readString(file);
        return fixLineEndings(contents);
    }

    public static void writeFile(Path file, String contents) throws IOException {
        try {
            Files.createFile(file);
        } catch(IOException ignored) {
            /* don't worry if the file already exists */
        }

        Files.writeString(file, fixLineEndings(contents));
    }

    public static void deleteFile(Path file) throws IOException {
        Files.delete(file);
    }

    /**
     * @deprecated use {@link EasyTime#getSeconds()} instead.
     */
    @Deprecated public static float timeToSeconds(int hours, int minutes, int seconds, int ms) {
        // the helper bot i use gets the seconds and milliseconds as ints,
        // i love inconsistency!
        int hoursAsMinutes = hours * 60;
        minutes += hoursAsMinutes;

        return (minutes * 60) + seconds + (ms * .001f);
    }

    /**
     * This is very broken I think. Maybe I should just remove this now...
     * <br>
     * (if you see this and its still in the code after March of 2022 (time of
     * writing), <a href="https://github.com/helpimnotdrowning/MochiCaps">raise an issue
     * on the repo</a>)
     *
     * @deprecated use {@link EasyTime#getTime()} instead.
     */
    @Deprecated public static String secondsToTime(float seconds) {
        // i love inconsistency!
        SimpleDateFormat format = new SimpleDateFormat("H:m:s.m");
        return format.format(new Date((int)(seconds * 1000)));
    }
}