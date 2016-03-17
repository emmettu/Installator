package views.ui.button;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;
import views.lookandfeel.ButtonFactory;

import javax.swing.*;

/**
 * Created by eunderhi on 10/12/15.
 */
public class GUIButton extends Button {

    private JButton button;

    public GUIButton() {
        this(ButtonFactory.createButton(""));
    }

    public GUIButton(JButton button) {
        this.button = button;
        button.addActionListener(actionEvent -> update());
    }

    @Override
    public void update() {
        notifyControllers();
    }

    @Override
    public void display() {
        button.repaint();
    }

    @Override
    public void setText(String text) {
        button.setText(text);
    }

    public JButton getButton() {
        return button;
    }

}
