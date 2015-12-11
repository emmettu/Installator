package views.ui.button;

import javax.swing.*;

/**
 * Created by eunderhi on 10/12/15.
 */
public class GUIButton extends Button {

    private JButton button = new JButton();

    public GUIButton() {
        button.setSize(6, 10);
        button.addActionListener(actionEvent -> update());
    }

    @Override
    public void update() {
        notifyControllers();
        System.out.println("Starting unpacking routine");
    }

    @Override
    public void display() {
        button.repaint();
    }

    public JButton getButton() {
        return button;
    }
}
