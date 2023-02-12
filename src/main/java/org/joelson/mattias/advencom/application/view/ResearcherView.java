package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.model.Researcher;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class ResearcherView {

    private final Researcher researcher;
    private final JLabel nameLabel;
    private final JComboBox<Integer> levelComboBox;
    private final JLabel effectLabel;

    public ResearcherView(Researcher researcher) {
        this.researcher = researcher;
        nameLabel = new JLabel(this.researcher.getName());
        levelComboBox = new JComboBox<>();
        levelComboBox.addItem(this.researcher.getLevel());
        effectLabel = new JLabel("[effect]");
    }

    public JComponent[] getComponents() {
        return new JComponent[] { nameLabel, levelComboBox, effectLabel };
    }
}
