package views.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 17/12/15.
 * The panel that allows the user to select a language
 */
public class LanguageSelectPanel extends GUIPanel {

    private JComboBox comboBox;

    @Override
    public void build(JPanel contentPanel) {
        contentPanel.removeAll();
        JPanel panel = new JPanel();
        panel.add(comboBox);
        contentPanel.add(panel, BorderLayout.NORTH);
    }

    public void setComboBox(JComboBox box) {
        comboBox = box;
    }

}
