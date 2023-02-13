package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.model.Researcher;

public class TierProductionView {

    private final RangedValueView valueView;
    private final ResearcherView researcherView;

    public TierProductionView(String productionName, Researcher researcher) {
        valueView = new RangedValueView(productionName);
        researcherView = new ResearcherView(researcher);
    }

    public RangedValueView getValueView() {
        return valueView;
    }

    public ResearcherView getResearcherView() {
        return researcherView;
    }
}
