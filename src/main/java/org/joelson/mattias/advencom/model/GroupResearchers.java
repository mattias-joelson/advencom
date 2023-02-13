package org.joelson.mattias.advencom.model;

public class GroupResearchers {

    private final Researcher powerResearcher;
    private final Researcher chanceResearcher;
    private final Researcher bonusResearcher;

    public GroupResearchers(Researcher powerResearcher, Researcher chanceResearcher, Researcher bonusResearcher) {
        this.powerResearcher = powerResearcher;
        this.chanceResearcher = chanceResearcher;
        this.bonusResearcher = bonusResearcher;
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
}
