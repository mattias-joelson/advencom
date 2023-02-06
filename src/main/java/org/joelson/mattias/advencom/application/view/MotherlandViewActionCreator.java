package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.events.Motherland;
import org.joelson.mattias.advencom.model.Amount;
import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;
import org.joelson.mattias.advencom.model.Researcher;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

        Container contentPane = new Container();
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentPane.setLayout(gridBagLayout);
        int y = 0;
        contentPane.add(new JLabel(event.getName()), constraints(0, y++, 6, 1));
        addResearcherView(contentPane, 0, 3, y++, event.getPowerResearcher(), event.getName());
        addResearcherView(contentPane, 0, 3, y++, event.getChanceResearcher(), event.getName());
        addResearcherView(contentPane, 0, 3, y++, event.getBonusResearcher(), event.getName());
        for (Industry industry : event.getIndustries()) {
            contentPane.add(new JLabel(industry.getResource()), constraints(0, y++, 6, 1));
            addResearcherView(contentPane, 0, 3, y++, industry.getPowerResearcher(), industry.getResource());
            addResearcherView(contentPane, 0, 3, y++, industry.getChanceResearcher(), industry.getResource());
            addResearcherView(contentPane, 0, 3, y++, industry.getBonusResearcher(), industry.getResource());
            contentPane.add(new JLabel(industry.getResource()), constraints(0, y, 1, 1));
            contentPane.add(new JTextField(6), constraints(1, y, 1, 1));
            contentPane.add(createRangeComboBox(), constraints(2, y++, 1, 1));
            for (Producer producer : industry.getProducers()) {
                contentPane.add(new JLabel(producer.getName()), constraints(0, y, 1, 1));
                contentPane.add(new JTextField(6), constraints(1, y, 1, 1));
                contentPane.add(createRangeComboBox(), constraints(2, y, 1, 1));
                addResearcherView(contentPane, 3, y, producer.getResearcher());
                y += 1;
            }
            contentPane.add(new JLabel("Goal"), constraints(0, y, 1, 1));
            contentPane.add(new JTextField(6), constraints(1, y, 1, 1));
            contentPane.add(createRangeComboBox(), constraints(2, y++, 1, 1));
            contentPane.add(new JButton("Calculate"), constraints(0, y, 1, 1));
            contentPane.add(new JLabel("[result]"), constraints(1, y++, 5, 1));
        }
        JCheckBox boostCheckBox = new JCheckBox("boost");
        boostCheckBox.setSelected(true);
        contentPane.add(boostCheckBox, constraints(0, y++, 6, 1));

        applicationUI.setPane(contentPane);
    }

    private static JComboBox<String> createRangeComboBox() {
        JComboBox<String> range = new JComboBox<>();
        range.addItem("");
        for (Amount.Range r : Amount.Range.values()) {
            range.addItem(r.name());
        }
        return range;
    }

    private static void addResearcherView(Container contentPane, int x, int rx, int y, Researcher researcher, String label) {
        contentPane.add(new JLabel(label + " " + researcher.getType() + ":"), constraints(x, y, 1, 1));
        addResearcherView(contentPane, rx, y, researcher);
    }

    private static void addResearcherView(Container contentPane, int x, int y, Researcher researcher) {
        contentPane.add(new JLabel(researcher.getName()), constraints(x, y, 1, 1));
        JComboBox<String> level = new JComboBox<>();
        level.addItem(Integer.toString(researcher.getLevel()));
        contentPane.add(level, constraints(x + 1, y, 1, 1));
        contentPane.add(new JLabel("[effect]"), constraints(x + 2, y, 1, 1));
    }

    private static final Insets INSETS = new Insets(1, 1, 1, 1);
    private static GridBagConstraints constraints(int x, int y, int width, int height) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = INSETS;
        return constraints;
    }
}
