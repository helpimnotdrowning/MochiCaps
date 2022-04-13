package io.github.helpimnotdrowning.mochicaps.nconfig;

import io.github.helpimnotdrowning.mochicaps.info.Bot;

public class Config {
    private String owner;

    private Bot[] bots;

    public Config(String owner, Bot... bots) {
        setOwner(owner);
        setBots(bots);
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setBots(Bot... bots) {
        this.bots = bots;
    }

    public Bot[] getBots() {
        return bots;
    }
}
