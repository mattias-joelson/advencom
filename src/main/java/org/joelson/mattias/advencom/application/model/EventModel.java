package org.joelson.mattias.advencom.application.model;

import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.GroupResearchers;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;
import org.joelson.mattias.advencom.model.Researcher;

import java.util.HashMap;
import java.util.Map;

public class EventModel {

    private final Event event;
    private final Map<String, Integer> researcherLevelMap;
    private final Map<String, Double> produceValueMap;

    public EventModel(Event event) {
        this.event = event;
        researcherLevelMap = new HashMap<>();
        produceValueMap = new HashMap<>();
        initEventModel();
    }

    private void initEventModel() {
        addGroupResearchers(event.getGlobalResearchers());
        for (Industry industry : event.getIndustries()) {
            initIndustry(industry);
        }
    }

    private void addGroupResearchers(GroupResearchers groupResearchers) {
        addResearcher(groupResearchers.getPowerResearcher());
        addResearcher(groupResearchers.getChanceResearcher());
        addResearcher(groupResearchers.getBonusResearcher());
    }

    private void addResearcher(Researcher researcher) {
        researcherLevelMap.put(researcher.getName(), researcher.getLevel());
    }

    private void initIndustry(Industry industry) {
        addGroupResearchers(industry.getIndustryResearchers());
        addResearcher(industry.getFirstProduceResearcher());
        produceValueMap.put(industry.getResource(), 0.0);
        for (Producer producer : industry.getProducers()) {
            produceValueMap.put(producer.getName(), 0.0);
            addResearcher(producer.getResearcher());
        }
    }

    public String getEventName() {
        return event.getName();
    }
}
