package org.joelson.mattias.advencom.model;

public class Event {

    private final String name;
    private final Researcher powerResearcher;
    private final Researcher chanceResearcher;
    private final Researcher bonusResearcher;
    private final Industry[] industries;

    public Event(String name, Researcher powerResearcher, Researcher chanceResearcher, Researcher bonusResearcher, Industry... industries) {
        this.name = name;
        this.powerResearcher = powerResearcher;
        this.chanceResearcher = chanceResearcher;
        this.bonusResearcher = bonusResearcher;
        this.industries = industries;
    }

    public String getName() {
        return name;
    }

    public Researcher getPowerResearcher() {
        return powerResearcher;
    }

    public Researcher getChanceResearcher() {
        return chanceResearcher;
    }

    public Researcher getBonusResearcher() {
        return bonusResearcher;
    }

    public Industry[] getIndustries() {
        return industries;
    }
}
