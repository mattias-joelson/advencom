package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.application.controller.EventController;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class IndustryView {

    private final RangedValueView resourceValueView;
    private final ResearcherView firstProduceResearcherView;
    private final TierProductionView[] tierProductionViews;
    private final RangedValueView goalValueView;

    public IndustryView(Industry industry, EventController eventController, LayoutToolkit toolkit) {
        toolkit.addLabel(industry.getResource());
        resourceValueView = new RangedValueView(industry.getResource());
        firstProduceResearcherView = new ResearcherView(industry.getFirstProduceResearcher());
        toolkit.addTwoViewComponents(resourceValueView.getComponents(), firstProduceResearcherView.getComponents());
        tierProductionViews = new TierProductionView[industry.getProducers().length];
        for (int i = 0; i < tierProductionViews.length; i += 1) {
            Producer producer = industry.getProducers()[i];
            TierProductionView tierProductionView = new TierProductionView(producer.getName(), producer.getResearcher());
            toolkit.addTwoViewComponents(tierProductionView.getValueView().getComponents(), tierProductionView.getResearcherView().getComponents());
            tierProductionViews[i] = tierProductionView;
        }
        goalValueView = new RangedValueView("Goal");
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> eventController.calculateProduction(this));
        toolkit.addTwoViewComponents(goalValueView.getComponents(), new JComponent[] { calculateButton, new JLabel(), new JLabel() });
    }
}
