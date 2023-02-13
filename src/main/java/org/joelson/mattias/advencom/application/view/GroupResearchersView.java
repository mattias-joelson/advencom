package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.model.GroupResearchers;

public class GroupResearchersView {

    private final ResearcherView powerResearcherView;
    private final ResearcherView chanceResearcherView;
    private final ResearcherView bonusResearcherView;

    public GroupResearchersView(GroupResearchers researchers, LayoutToolkit toolkit, String labelFormatString) {
        powerResearcherView = new ResearcherView(researchers.getPowerResearcher());
        toolkit.addLabelAndViewComponents(String.format(labelFormatString, "power"), powerResearcherView.getComponents());
        chanceResearcherView = new ResearcherView(researchers.getChanceResearcher());
        toolkit.addLabelAndViewComponents(String.format(labelFormatString, "chance"), chanceResearcherView.getComponents());
        bonusResearcherView = new ResearcherView(researchers.getBonusResearcher());
        toolkit.addLabelAndViewComponents(String.format(labelFormatString, "bonus"), bonusResearcherView.getComponents());
    }

    public ResearcherView getPowerResearcherView() {
        return powerResearcherView;
    }

    public ResearcherView getChanceResearcherView() {
        return chanceResearcherView;
    }

    public ResearcherView getBonusResearcherView() {
        return bonusResearcherView;
    }
}
