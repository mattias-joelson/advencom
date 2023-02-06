package org.joelson.mattias.advencom.model;

public class Industry {

    private final String resource;
    private final Researcher powerResearcher;
    private final Researcher chanceResearcher;
    private final Researcher bonusResearcher;
    private final Producer[] producers;

    public Industry(String resource, Researcher powerResearcher, Researcher chanceResearcher, Researcher bonusResearcher, Producer... producers) {
        this.resource = resource;
        this.powerResearcher = powerResearcher;
        this.chanceResearcher = chanceResearcher;
        this.bonusResearcher = bonusResearcher;
        this.producers = producers;
    }

    public String getResource() {
        return resource;
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

    public Producer[] getProducers() {
        return producers;
    }
}
