package views.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 17/12/15.
 * The panel that l
 */
public class LanguageSelectPanel extends GUIPanel {

    private JComboBox comboBox;

    @Override
    public void build(JPanel contentPanel) {
        contentPanel.removeAll();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipadx = 0;
        constraints.ipady = 6;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 3;
        layout.addLayoutComponent(comboBox, constraints);

        contentPanel.add(comboBox);
        contentPanel.setLayout(layout);
    }

    public void setComboBox(JComboBox box) {
        comboBox = box;
    }

}
