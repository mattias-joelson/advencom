package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.application.controller.EventController;
import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.Industry;

import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;

public class EventView {

    private final JPanel contentPane;

    private final Event event;
    private final GroupResearchersView eventResearcherView;
    private final Map<String, IndustryView> industryViews;

    public EventView(Event event, EventController controller) {
        contentPane = new JPanel();
        LayoutToolkit toolkit = new LayoutToolkit(contentPane);

        this.event = event;
        toolkit.addLabel(event.getName());
        eventResearcherView = new GroupResearchersView(event.getGlobalResearchers(), toolkit, event.getName() + " %s resarcher");

        industryViews = new HashMap<>();
        for (Industry industry : this.event.getIndustries()) {
            industryViews.put(industry.getResource(), new IndustryView(industry, controller, toolkit));
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
