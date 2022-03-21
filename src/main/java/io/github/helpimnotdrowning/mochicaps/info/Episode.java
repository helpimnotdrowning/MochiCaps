package io.github.helpimnotdrowning.mochicaps.info;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Dataclass to hold episode information.
 */
public class Episode implements IBetterRepresentation {
    private final String title;
    private final Path path;

    public Episode(String title, Path path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String friendlyString() {
        return getTitle();
    }

    @Override
    public String infoString() {
        return String.format("Episode[\"%s\" at %s]", getTitle(), getPath());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Episode other) {
            // check if path is the same, the name doesn't actually matter for this
            return Objects.equals(getPath(), other.getPath());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath());
    }
}
