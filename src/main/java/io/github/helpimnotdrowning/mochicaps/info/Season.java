package io.github.helpimnotdrowning.mochicaps.info;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;

import java.util.Arrays;
import java.util.Objects;

/**
 * Dataclass to hold season information.
 */
public class Season implements IBetterRepresentation {
    private final String title;
    private final Episode[] episodes;

    public Season(String title, Episode... episodes) {
        this.title = title;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    @Override
    public String friendlyString() {
        return getTitle();
    }

    @Override
    public String infoString() {
        return String.format("Season[\"%s\" with %s episodes]", getTitle(), getEpisodes().length);
    }

    @Override
    public String debugString() {
        StringBuilder episodeList = new StringBuilder();
        for (Episode episode: getEpisodes()) {
            episodeList.append(String.format("        %s\n", episode.debugString()));
        }

        return String.format("Season[\"%s\" with %s episodes:\n%s    ]", getTitle(), getEpisodes().length, episodeList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Season other) {
            // check if path is the same, the name doesn't actually matter for this
            return Arrays.equals(getEpisodes(), other.getEpisodes());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object)getEpisodes());
    }
}
