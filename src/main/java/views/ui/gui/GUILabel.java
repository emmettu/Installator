package views.ui.gui;

import javax.swing.*;
import java.awt.*;

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

    public void setColor(Color color) {
        jLabel.setForeground(color);
    }

}
