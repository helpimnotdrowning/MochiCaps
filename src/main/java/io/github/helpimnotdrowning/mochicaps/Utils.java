package io.github.helpimnotdrowning.mochicaps;

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
        System.exit(69);
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
     * Censor a String by keeping a few characters at the beginning and adding 3 *s (asterisks) to the end.
     * @param message The message to be censored.
     * @return The censored String.
     */
    public static String censor(String message) {
        return message.substring(0, UNCENSORED_LENGTH) + "***";
    }

    /**
     * Censor a String by keeping a few characters at the beginning and replacing the rest with *s (asterisks)
     * @param message The message to be censored.
     * @return The censored String.
     */
    public static String censorFull(String message) {
        return message.substring(0, UNCENSORED_LENGTH) + '*' * (message.length() - UNCENSORED_LENGTH);
    }

    /**
     * Read a file to a String.
     * @param file The Path of the file to be read.
     * @return The whole contents of the file as a String.
     * @throws IOException If something goes wrong while reading the file. The method {@code Files.readString} writes it
     * as "if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read"
     */
    public static String readFile(Path file) throws IOException {
        String contents = Files.readString(file);
        return fixLineEndings(contents);
    }

    /**
     * Write a String into a file, overwriting it in the process, and creating it if the file doesn't exist.
     * @param file The Path of the file to be written to.
     * @param contents The String to be written into the file
     * @throws IOException If something goes wrong while writing the file. The method {@code Files.writeString} writes
     * it as "if an I/O error occurs writing to or creating the file, or the text cannot be encoded using the specified
     * charset"
     */
    public static void writeFile(Path file, String contents) throws IOException {
        createFileIfNotExists(file);
        Files.writeString(file, fixLineEndings(contents));
    }

    /**
     * Delete a file.
     * @param file The Path of the file to be deleted.
     * @throws IOException If something goes wrong while deleting the file.
     */
    public static void deleteFile(Path file) throws IOException {
        // guard to prevent deleting a folder
        // that would be bad, also there is no need to ever remove a directory.
        if (Files.isDirectory(file)) {
            LOGGER.error(String.format("The path %s will not be deleted by deleteFile() because it is a directory, " +
                    "not a file!", file));
            System.exit(1);
        }

        Files.delete(file);
    }

    /**
     * Create a file only if it doesn't already exist.
     * @param file The Path of the file to be created.
     */
    public static void createFileIfNotExists(Path file) {
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                LOGGER.error("Couldn't create file at \"" + file.toAbsolutePath() + "\"!");
                e.printStackTrace();
                System.exit(1);
            }
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