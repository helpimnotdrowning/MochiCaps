package net.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final int UNCENSORED_LENGTH = 4;

    private static final Log LOGGER = new Log(Utils.class);

    private Utils() {
        LOGGER.error("this is a utils class stop trying to instantiate it!!!!");
        System.exit(Integer.MAX_VALUE);
    }

    private static void crash(String message) {
        LOGGER.error(message);
        System.exit(1);
    }

    static void crash(String message, Throwable e) {
        LOGGER.error(message);
        e.printStackTrace();
        System.exit(1);
    }

    // https://stackoverflow.com/a/9375063
    /**
     * Convert DOS line endings (CRLF, \r\n) to Unix line endings (LF, \n).
     * @param DOSText A String with DOS line endings.
     * @return A String with Unix line endings.
     */
    public static String fixLineEndings(String DOSText) {
        return DOSText.replaceAll("\r\n", "\n");
    }

    /**
     * Censor a String by keeping UNCENSORED_LENGTH characters at the beginning and replacing the rest with "*"
     * @param message The message to be censored.
     * @return The censored message.
     */
    public static String censor(String message) {
        return message.substring(0, UNCENSORED_LENGTH) + "*".repeat(message.length() - UNCENSORED_LENGTH);
    }

    /**
     * Censor a String by replacing it with *s (asterisks)
     * @param message The message to be censored.
     * @return The censored message.
     */
    public static String censorFull(String message) {
        return "*".repeat(message.length());
    }

    /**
     * Read a file to a String.
     * @param file The Path of the file to be read.
     * @return The contents of the file as a String.
     */
    public static String readFile(Path file) {
        try {
            return Files.readString(file);

        } catch (IOException ex) {
            crash(String.format("The file '%s' couldn't be read", file), ex);
            return "THIS TEXT SHOULD NEVER BE SEEN"; // need to return something, even if it never actually will
        }
    }

    /**
     * Write a String into a file (overwriting it in the process) and creating it if the file doesn't exist.
     * @param file The Path of the file to be written to.
     * @param contents The String to be written into the file
     */
    public static void writeFile(Path file, String contents) {
        try {
            createFile(file);
            Files.writeString(file, fixLineEndings(contents));
        } catch (IOException ex) {
            crash(String.format("The file '%s' couldn't be written", file), ex);
        }
    }

    /**
     * Delete a file.
     * @param file The Path of the file to be deleted.
     * @throws IOException If something goes wrong while deleting the file.
     */
    public static void deleteFile(Path file) throws IOException {
        // MochiCaps should never have a reason to delete a directory
        if (Files.isDirectory(file)) {
            throw new IOException(String.format("The path '%s' will not be deleted because it is a directory, not a file!", file));
        }

        Files.delete(file);
    }

    /**
     * Create a file only if it doesn't already exist.
     * @param file The Path of the new file
     */
    public static void createFile(Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            LOGGER.error(String.format("Couldn't create file at '%s'!", file.toAbsolutePath()));
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void safeShutdown(int code) {
        LOGGER.info("this shutdown is not safe (yet)!!!");
        System.exit(code);
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
