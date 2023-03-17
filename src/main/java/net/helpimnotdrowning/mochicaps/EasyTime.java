package net.helpimnotdrowning.mochicaps;

import java.util.Objects;

/**
 * Simplify conversions between raw time and seconds
 */
public class EasyTime /*implements IBetterRepresentation*/ {
    private final float sec;

    public EasyTime(int hours, int minutes, int seconds, int ms) {
        this.sec = ((minutes + (hours * 60)) * 60) + seconds + (ms * .001f);
    }

    public EasyTime(float seconds) {
        this.sec = seconds;
    }

    public float getSeconds() {
        return this.sec;
    }

    public String getTime() {
        int hours = (int)Math.floor(this.sec / 3600);
        int minutes = (int)Math.floor((this.sec % 3600) / 60);
        int seconds = (int)Math.floor(this.sec % 60);
        int ms = (int)((this.sec % 1) * 1000);

        return String.format("%s:%s:%s.%s", hours, minutes, seconds, ms);
    }

    /*@Override*/
    public String friendlyString() {
        return String.format("%s (%s)", getTime(), getSeconds());
    }

    /*@Override*/
    public String infoString() {
        return String.format("EasyTime[%s (%s seconds)]", getTime(), getSeconds());
    }

    /*@Override*/
    public String debugString() {
        return infoString();
    }

    /*@Override*/
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EasyTime other) {
            return Objects.equals(getSeconds(), other.getSeconds());
        } else {
            return false;
        }
    }

    /*@Override*/
    public int hashCode() {
        return Objects.hash(getSeconds());
    }

}