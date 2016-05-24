package views.ui.gui;

import views.lookandfeel.ButtonFactory;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;

/**
 * Created by eunderhi on 08/02/16.
 */

public class GUIButton extends GUIComponent implements Button {

    private JButton button;

    public GUIButton(String text) {
        button = ButtonFactory.createButton(text);
        setJComponent(button);
        button.addActionListener(event -> onClick());
    }

    public void setUI(ButtonUI ui) {
        button.setUI(ui);
    }

    public void setForeground(Color color) {
        button.setForeground(color);
    }

    @Override
    public void onClick() {
        update();
    }

    public void setText(String text) {
        button.setText(text);
    }

}
