package net.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final Log LOGGER = new Log(Utils.class);

    private Utils() {
        LOGGER.error("this is a utils class stop trying to instantiate it!!!!");
        System.exit(Integer.MAX_VALUE);
    }

    /**
     * Exits the program and prints the stack trace. Try not to use this.
     * @param message Exit message to be logged.
     */
    public static void crash(String message) {
        LOGGER.error(message);
        System.exit(1);
    }

    /**
     * Exits the program and prints the stack trace. Try not to use this.
     * @param message Exit message to be logged.
     * @param e Exception
     */
    public static void crash(String message, Throwable e) {
        LOGGER.error(message);
        e.printStackTrace();
        System.exit(1);
    }

    /**
     * Convert DOS line endings (CRLF, \r\n) to Unix line endings (LF, \n).
     * Via <a href="https://stackoverflow.com/a/9375063">stackoverflow</a>
     * @param message A String potentially with or without DOS line endings.
     * @return A String guaranteed to have Unix line endings.
     */
    public static String fixLineEndings(String message) {
        return message.replaceAll("\r\n", "\n");
    }

    /**
     * Censor a {@code message} by replacing all characters after {@code uncensoredLength} with asterisks.
     * @param message Message to be censored
     * @param uncensoredLength Number of characters to keep uncensored
     * @return The censored message
     * @throws IllegalArgumentException When the {@code uncensoredLength} is either greater than the {@code message}
     * length or less than 0
     */
    public static String censor(String message, int uncensoredLength) throws IllegalArgumentException {
        if (uncensoredLength > message.length()) {
            throw new IllegalArgumentException(String.format("String uncensoredLength %s was greater than the message length %s.", uncensoredLength, message.length()));
        }

        if (uncensoredLength < 0) {
            throw new IllegalArgumentException(String.format("String uncensoredLength %s was less than 0.", uncensoredLength));
        }

        return message.substring(0, uncensoredLength) + "*".repeat(message.length() - uncensoredLength);
    }

    public static String censor(String message) {
        int uncensoredLength = 3;
        if (uncensoredLength > message.length()) {
            return censor(message, message.length());
        }

        return censor(message, uncensoredLength);
    }

    /**
     * Read a file to a String.
     * @param file The Path of the file to be read.
     * @return The contents of the file as a String.
     */
    public static String readFile(Path file) throws IOException {
        try {
            return Files.readString(file);
        } catch (IOException ex) {
            throw new IOException(String.format("The file '%s' couldn't be read", file.toAbsolutePath()), ex);
        }
    }

    /**
     * Write a String into a file (overwriting it in the process) and creating it if the file doesn't exist.
     * @param file The Path of the file to be written to.
     * @param contents The String to be written into the file
     */
    public static void writeFile(Path file, String contents) throws IOException {
        if (!Files.exists(file)) {
            createFile(file);
        }

        try {
            Files.writeString(file, fixLineEndings(contents));
        } catch (IOException ex) {
            LOGGER.warn(String.format("The file '%s' couldn't be written for some reason!", file.toAbsolutePath()));
            LOGGER.debug(String.format("File '%s' failed to be written, here are it's contents:", file.toAbsolutePath()));
            LOGGER.debug(contents); // "but what if this was supposed to be a really long file?" /shrug
            LOGGER.debug(String.format("End file '%s' contents.", file.toAbsolutePath()));
            throw new IOException(String.format("The file '%s' couldn't be written", file.toAbsolutePath()), ex);
        }
    }

    /**
     * Delete a file.
     * @param file File to be deleted
     * @throws IOException If something goes wrong while deleting the file
     * @throws IllegalArgumentException If the file is a directory
     */
    public static void deleteFile(Path file) throws IOException, IllegalArgumentException {
        // MochiCaps should never have a reason to delete a directory!!!
        if (Files.isDirectory(file)) {
            LOGGER.warn(String.format("The path '%s' will not be deleted because it is a directory, not a file!", file));
            throw new IllegalArgumentException(String.format("The path '%s' will not be deleted because it is a directory, not a file!", file));
        }

        try {
            Files.delete(file);
        } catch (IOException ex) {
            throw new IOException("The path '%s' couldn't be deleted for some reason!", ex);
        }
    }
    
    /**
     * Create a file
     * @param file The Path of the new file
     */
    public static void createFile(Path file) throws IOException {
        try {
            Files.createFile(file);
        } catch (IOException ex) {
            LOGGER.warn(String.format("Couldn't create file at '%s'!", file.toAbsolutePath()));
            throw new IOException(String.format("Couldn't create file at '%s'!", file.toAbsolutePath()), ex);
        }
    }

    /**
     * Save and exit (but not yet)
     * @param code Exit code
     */
    public static void safeShutdown(int code) {
        LOGGER.info("this shutdown is not safe (yet)!!!");
        System.exit(code);
    }

    /**
     * @deprecated use {@link EasyTime#getSeconds()} instead.
     */
    @Deprecated public static float timeToSeconds(int hours, int minutes, int seconds, int ms) {
        // the helper bot I use gets the seconds and milliseconds as ints,
        // I love inconsistency!
        int hoursAsMinutes = hours * 60;
        minutes += hoursAsMinutes;
        
        return (minutes * 60) + seconds + (ms * .001f);
    }

    /**
     * This is very broken I think. Maybe I should just remove this now...
     *
     * @deprecated use {@link EasyTime#getTime()} instead.
     */
    @Deprecated public static String secondsToTime(float seconds) {
        // i love inconsistency!
        SimpleDateFormat format = new SimpleDateFormat("H:m:s.m");
        return format.format(new Date((int)(seconds * 1000)));
    }
}
