package org.joelson.mattias.advencom.model;

public class Event {

    private final String name;
    private final GroupResearchers globalResearchers;
    private final Industry[] industries;

    public Event(String name, Researcher powerResearcher, Researcher chanceResearcher, Researcher bonusResearcher, Industry... industries) {
        this.name = name;
        this.globalResearchers = new GroupResearchers(powerResearcher, chanceResearcher, bonusResearcher);
        this.industries = industries;
    }

    public String getName() {
        return name;
    }

    public GroupResearchers getGlobalResearchers() {
        return globalResearchers;
    }

    public Industry[] getIndustries() {
        return industries;
    }
}
