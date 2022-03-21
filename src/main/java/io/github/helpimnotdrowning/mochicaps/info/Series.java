package io.github.helpimnotdrowning.mochicaps.info;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;

import java.util.Arrays;
import java.util.Objects;

/**
 * Dataclass to hold series information.
 */
public class Series implements IBetterRepresentation {
    private final String title;
    private final Season[] seasons;

    public Series(String title, Season... seasons) {
        this.title = title;
        this.seasons = seasons;
    }

    public String getTitle() {
        return title;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    @Override
    public String friendlyString() {
        return getTitle();
    }

    @Override
    public String infoString() {
        return String.format("Series[\"%s\" with %s seasons]", getTitle(), getSeasons().length);
    }

    @Override
    public String debugString() {
        StringBuilder seasonList = new StringBuilder();
        for (Season season: getSeasons()) {
            seasonList.append(String.format("    %s\n", season.debugString()));
        }
        return String.format("Series[\"%s\" with %s seasons:\n%s]", getTitle(), getSeasons().length, seasonList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Series other) {
            // check if path is the same, the name doesn't actually matter for this
            return Arrays.equals(getSeasons(), other.getSeasons());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object)getSeasons());
    }
}
