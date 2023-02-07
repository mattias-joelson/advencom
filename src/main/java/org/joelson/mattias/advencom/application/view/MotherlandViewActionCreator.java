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

    public static Action create(ApplicationUI applicationUI) {
        return new ActionBuilder(actionEvent -> showMotherlandView(applicationUI))
                .withName("Show Motherland")
//                .withAcceleratorKey(KeyEvent.VK_U)
                .build();
    }

    private static void showMotherlandView(ApplicationUI applicationUI) {
        Event event = Motherland.getMotherlandEvent();
        JPanel contentPane = new JPanel();
        LayoutToolkit toolkit = new LayoutToolkit(contentPane);

        toolkit.addLabel(event.getName());
        toolkit.addLabelAndControl(event.getName() + " Power Researcher", new ResearcherView(event.getPowerResearcher()).getControls());
        toolkit.addLabelAndControl(event.getName() + " Chance Researcher", new ResearcherView(event.getChanceResearcher()).getControls());
        toolkit.addLabelAndControl(event.getName() + " Bonus Researcher", new ResearcherView(event.getBonusResearcher()).getControls());

        for (Industry industry : event.getIndustries()) {
            toolkit.addLabel(industry.getResource());
            toolkit.addLabelAndControl(industry.getResource() + " Power Researcher", new ResearcherView(industry.getPowerResearcher()).getControls());
            toolkit.addLabelAndControl(industry.getResource() + " Chance Researcher", new ResearcherView(industry.getChanceResearcher()).getControls());
            toolkit.addLabelAndControl(industry.getResource() + " Bonus Researcher", new ResearcherView(industry.getBonusResearcher()).getControls());

            toolkit.addControl(new RangedValueView(industry.getResource()).getControls());
            for (Producer producer : industry.getProducers()) {
                toolkit.addControlAndControl(new RangedValueView(producer.getName()).getControls(), new ResearcherView(producer.getResearcher()).getControls());
            }
            toolkit.addControl(new RangedValueView("Goal").getControls());
            JCheckBox boostCheckBox = new JCheckBox("boost");
            boostCheckBox.setSelected(true);
            JButton calculateButton = new JButton("Calculate");
            toolkit.addControl(new JComponent[]{ boostCheckBox, calculateButton, new JLabel()});
        }

        applicationUI.setPane(contentPane);
    }
}
