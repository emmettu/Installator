package views.notification;

import views.lookandfeel.UiResources;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 19/01/16.
 * simple timed pop-up message
 */

public class ToastMessage extends JDialog {

    public ToastMessage(String message, int time, JComponent component) {
        JPanel panel = new JPanel();

        getContentPane().setBackground(Color.WHITE);
        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        panel.setBackground(UiResources.blueButtonLight);
        panel.add(label);
        getContentPane().add(panel);

        setLocationRelativeTo(component);
        setAlwaysOnTop(true);
        setUndecorated(true);
        pack();
        setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(time);
                dispose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
