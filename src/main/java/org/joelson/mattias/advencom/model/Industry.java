package org.joelson.mattias.advencom.model;

public class Industry {

    private final String resource;
    private final GroupResearchers industryResearchers;
    private final Researcher firstProduceResearcher;
    private final Producer[] producers;

    public Industry(String resource, Researcher powerResearcher, Researcher chanceResearcher, Researcher bonusResearcher, Researcher firstProduceResearcher, Producer... producers) {
        this.resource = resource;
        this.industryResearchers = new GroupResearchers(powerResearcher, chanceResearcher, bonusResearcher);
        this.firstProduceResearcher = firstProduceResearcher;
        this.producers = producers;
    }

    public String getResource() {
        return resource;
    }

    public GroupResearchers getIndustryResearchers() {
        return industryResearchers;
    }

    public Researcher getFirstProduceResearcher() {
        return firstProduceResearcher;
    }

    public Producer[] getProducers() {
        return producers;
    }
}
