package io.github.helpimnotdrowning.mochicaps;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrameExtractor {
    public static void Extractor(EasyTime time, Path path) throws IOException {
        final Path tmpFileName = Paths.get("tmpFrameExtractor.png");

        Process ffmpeg = new ProcessBuilder("ffmpeg", "-y", "-loglevel", "warning", "-ss",
                time.getTime().toString(), "-i", path.toString(), "-vframes", "1", tmpFileName.toString()).start();
    }
}
