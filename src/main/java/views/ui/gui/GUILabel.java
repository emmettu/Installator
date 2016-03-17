package views.ui.gui;

import javax.swing.*;

/**
 * Created by eunderhi on 17/03/16.
 */
public class GUILabel extends GUIComponent implements Label {

    private JLabel jLabel = new JLabel();

    public GUILabel() {
        setJComponent(jLabel);
    }

    @Override
    public void setText(String text) {
        jLabel.setText(text);
    }

    @Override
    public String getText() {
        return jLabel.getText();
    }

}
