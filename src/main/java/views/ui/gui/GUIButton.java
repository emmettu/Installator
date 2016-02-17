package views.ui.gui;

import views.lookandfeel.ButtonFactory;

import javax.swing.*;

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

    @Override
    public void onClick() {
        notifyControllers();
    }

    public void setText(String text) {
        button.setText(text);
    }

}
