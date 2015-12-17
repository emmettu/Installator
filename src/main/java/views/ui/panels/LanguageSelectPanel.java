package views.ui.panels;

import javax.swing.*;

/**
 * Created by eunderhi on 17/12/15.
 */
public class LanguageSelectPanel extends GUIPanel {

    @Override
    public void build() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new JTextField());
    }
}
