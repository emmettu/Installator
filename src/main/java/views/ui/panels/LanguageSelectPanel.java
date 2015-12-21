package views.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 17/12/15.
 */
public class LanguageSelectPanel extends GUIPanel {

    private JComboBox comboBox;

    @Override
    public void build(JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.add(comboBox, BorderLayout.NORTH);
    }

    public void setComboBox(JComboBox box) {
        comboBox = box;
    }

}
