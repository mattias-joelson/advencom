package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.model.Amount;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RangedValueView {

    private final JLabel label;
    private final JTextField value;
    private final JComboBox<String> range;

    public RangedValueView(String labelText) {
        label = new JLabel(labelText);
        value = new JTextField(6);
        range = createRangeComboBox();
    }

    public JComponent[] getControls() {
        return new JComponent[] { label, value, range };
    }

    private static JComboBox<String> createRangeComboBox() {
        JComboBox<String> range = new JComboBox<>();
        range.addItem("");
        for (Amount.Range r : Amount.Range.values()) {
            range.addItem(r.name());
        }
        return range;
    }
}
