package io.github.helpimnotdrowning.mochicaps.nconfig;

import io.github.helpimnotdrowning.mochicaps.Log;
import io.github.helpimnotdrowning.mochicaps.Utils;
import io.github.helpimnotdrowning.mochicaps.info.Episode;
import io.github.helpimnotdrowning.mochicaps.info.Season;
import io.github.helpimnotdrowning.mochicaps.info.Series;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainConfig {
    private static final Log LOGGER = new Log(MainConfig.class);

    private static final Path baseConfigPath = Paths.get(System.getProperty("user.home"), "MochiCaps");
    private static final Path mainConfigPath = baseConfigPath.resolve("main.json");

    public MainConfig() {
        Utils.createFileIfNotExists(mainConfigPath);


    }

    private void createDummyConfig() {
        Series dummy =
            new Series("SERIES NAME",
                new Season("SEASON NAME (Season 01)",
                    new Episode("EPISODE 01", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e01.mkv")),
                    new Episode("EPISODE 02", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e02.mkv")),
                    new Episode("EPISODE 03", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e03.mkv")),
                    new Episode("EPISODE 04", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e04.mkv")),
                    new Episode("EPISODE 05", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e05.mkv")),
                    new Episode("EPISODE 06", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON1/e06.mkv"))
                ),
                new Season("SEASON NAME (Season 02)",
                    new Episode("EPISODE 01", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e01.mkv")),
                    new Episode("EPISODE 02", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e02.mkv")),
                    new Episode("EPISODE 03", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e03.mkv")),
                    new Episode("EPISODE 04", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e04.mkv")),
                    new Episode("EPISODE 05", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e05.mkv")),
                    new Episode("EPISODE 06", Paths.get("W:/Path/To/LegallyObtainedAnime/SERIES/SEASON2/e06.mkv"))
                )
            );

        LOGGER.info(dummy.debugString());
    }
}
