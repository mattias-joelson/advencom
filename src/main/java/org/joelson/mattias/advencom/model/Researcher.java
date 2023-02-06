package org.joelson.mattias.advencom.model;

public class Researcher {

    private final String name;
    private final ResearcherType type;
    private final ResearcherRarity rarity;
    private final int level;

    public Researcher(String name, ResearcherType type, ResearcherRarity rarity, int level) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public ResearcherType getType() {
        return type;
    }

    public ResearcherRarity getRarity() {
        return rarity;
    }

    public int getLevel() {
        return level;
    }
}
