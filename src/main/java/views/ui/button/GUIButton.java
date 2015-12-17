package views.ui.button;

import javax.swing.*;

/**
 * Created by eunderhi on 10/12/15.
 */
public class GUIButton extends Button {

    private JButton button = new JButton();

    public GUIButton() {
        button.setSize(6, 15);
        button.addActionListener(actionEvent -> update());
    }

    @Override
    public void update() {
        new Thread(this::notifyControllers).start();
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
