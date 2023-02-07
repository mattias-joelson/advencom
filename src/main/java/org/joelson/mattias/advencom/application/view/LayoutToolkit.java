package org.joelson.mattias.advencom.application.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LayoutToolkit {

    private final JPanel container;
    private int row;

    public LayoutToolkit(JPanel container) {
        this.container = container;
        this.row = 0;
        container.setLayout(new GridBagLayout());
    }

    public void addLabel(String labelText) {
        container.add(new JLabel(labelText), constraints(0, row++, 6, 1));
    }

    public void addControl(JComponent[] controls) {
        addControls(controls, 0);
        row += 1;
    }

    public void addLabelAndControl(String labelText, JComponent[] controls) {
        container.add(new JLabel(labelText), constraints(0, row, 3, 1));
        addControls(controls, 3);
        row += 1;
    }

    public void addControlAndControl(JComponent[] controls1, JComponent[] controls2) {
        addControls(controls1, 0);
        addControls(controls2, 3);
        row += 1;
    }

    private void addControls(JComponent[] controls, int offset) {
        if (controls.length != 3) {
            throw new IllegalArgumentException("Invalid number of controls - " + controls.length);
        }
        for (int i = 0; i < controls.length; i++) {
            container.add(controls[i], constraints(i + offset, row, 1, 1));
        }
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
