package views.notification;

import views.lookandfeel.UiResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by eunderhi on 19/01/16.
 * simple timed pop-up message
 */

public class ToastMessage extends JDialog {

    public ToastMessage(String message, int time, JComponent component) {
        JPanel panel = new JPanel();

        getContentPane().setBackground(Color.WHITE);
        JLabel label = new JLabel(message);
        panel.setBackground(UiResources.validationFail);
        panel.add(label);
        getContentPane().add(panel);

        destroyOnClick();

        setAlwaysOnTop(true);
        setUndecorated(true);

        setBounds(component.getBounds());
        setLocationRelativeTo(component);
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

    private void destroyOnClick() {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

}
