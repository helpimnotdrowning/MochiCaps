package io.github.helpimnotdrowning.mochicaps.info;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;
import io.github.helpimnotdrowning.mochicaps.credentials.Credentials;

public class Bot implements IBetterRepresentation {
    private String name;
    private String intervalString;
    private int intervalAmount;
    private char intervalUnit;
    private Credentials account;
    private Series series;

    public Bot(String name, String intervalString, Credentials account, Series series) {
        setName(name);
        setInterval(intervalString);
        setAccount(account);
        setSeries(series);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterval(String intervalString) {
        this.intervalString = intervalString;
        // intervalString is something like `30M`, `1H`, `15M`, etc.
        // this line gets the numbers out (30, 1, 15, ...)
        this.intervalAmount = Integer.parseInt(intervalString.substring(0, intervalString.length()-1));
        // this then gets the unit out (M, H, ...)
        this.intervalUnit = intervalString.charAt(intervalString.length()-1);
    }

    public void setAccount(Credentials account) {
        this.account = account;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String friendlyString() {
        return String.format("Bot[Bot \"%s\" is posting every %s]", name, intervalString);
    }

    @Override
    public String infoString() {
        return String.format("Bot[Bot \"%s\", interval is %s, series is %s]", name, intervalString, series.getTitle());
    }

    @Override
    public String debugString() {
        return infoString();
    }
}
