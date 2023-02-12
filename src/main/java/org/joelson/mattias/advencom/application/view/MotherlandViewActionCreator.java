package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.events.Motherland;
import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MotherlandViewActionCreator {

    private MotherlandViewActionCreator() throws IllegalAccessException {
        throw new IllegalAccessException("Should not be instantiated!");
    }

    public static Action create(ApplicationView applicationView) {
        return new ActionBuilder(actionEvent -> showMotherlandView(applicationView))
                .withName("Show Motherland")
//                .withAcceleratorKey(KeyEvent.VK_U)
                .build();
    }

    private static void showMotherlandView(ApplicationView applicationView) {
        Event event = Motherland.getMotherlandEvent();
        JPanel contentPane = new JPanel();
        LayoutToolkit toolkit = new LayoutToolkit(contentPane);

        toolkit.addLabel(event.getName());
        toolkit.addLabelAndControl(event.getName() + " Power Researcher", new ResearcherView(event.getPowerResearcher()).getComponents());
        toolkit.addLabelAndControl(event.getName() + " Chance Researcher", new ResearcherView(event.getChanceResearcher()).getComponents());
        toolkit.addLabelAndControl(event.getName() + " Bonus Researcher", new ResearcherView(event.getBonusResearcher()).getComponents());

        for (Industry industry : event.getIndustries()) {
            toolkit.addLabel(industry.getResource());
            toolkit.addLabelAndControl(industry.getResource() + " Power Researcher", new ResearcherView(industry.getPowerResearcher()).getComponents());
            toolkit.addLabelAndControl(industry.getResource() + " Chance Researcher", new ResearcherView(industry.getChanceResearcher()).getComponents());
            toolkit.addLabelAndControl(industry.getResource() + " Bonus Researcher", new ResearcherView(industry.getBonusResearcher()).getComponents());

            toolkit.addControl(new RangedValueView(industry.getResource()).getComponents());
            for (Producer producer : industry.getProducers()) {
                toolkit.addControlAndControl(new RangedValueView(producer.getName()).getComponents(), new ResearcherView(producer.getResearcher()).getComponents());
            }
            toolkit.addControl(new RangedValueView("Goal").getComponents());
            JCheckBox boostCheckBox = new JCheckBox("boost");
            boostCheckBox.setSelected(true);
            JButton calculateButton = new JButton("Calculate");
            toolkit.addControl(new JComponent[]{ boostCheckBox, calculateButton, new JLabel()});
        }

        applicationView.setPane(contentPane);
    }
}
