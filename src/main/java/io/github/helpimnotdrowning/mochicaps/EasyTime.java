package io.github.helpimnotdrowning.mochicaps;

import java.util.Objects;

/**
 * {@code EasyTime} makes dealing with converting time easier (i think). Instead of having a back and forth between
 * {@code time_to_seconds(h,m,s,ms)} and {@code seconds_to_time(seconds)}, {@code EasyTime} can do all of that with the
 * magic of
 * <br>
 * ~🌈~🌈 method calls 🌈~🌈~
 */
public class EasyTime implements IBetterRepresentation {
    private final float SECONDS;

    public EasyTime(int hours, int minutes, int seconds, int ms) {
        this.SECONDS = ((minutes + (hours * 60)) * 60) + seconds + (ms * .001f);
    }
    public float getSeconds() {
        return this.SECONDS;
    }

    public String getTime() {
        int hours = (int)Math.floor(this.SECONDS / 3600);
        int minutes = (int)Math.floor((this.SECONDS % 3600) / 60);
        int seconds = (int)Math.floor(this.SECONDS % 60);
        int ms = (int)((this.SECONDS % 1) * 1000);

        return String.format("%s:%s:%s.%s", hours, minutes, seconds, ms);
    }

    @Override
    public String friendlyString() {
        return String.format("%s (%s)", getTime(), getSeconds());
    }

    @Override
    public String infoString() {
        return String.format("EasyTime[%s (%s seconds)]", getTime(), getSeconds());
    }

    @Override
    public String debugString() {
        return infoString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EasyTime other) {
            return Objects.equals(getSeconds(), other.getSeconds());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeconds());
    }

}
