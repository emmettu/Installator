package views.notification;

import views.lookandfeel.UiResources;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 05/02/16.
 */
public class ToastMessage2 {

    public ToastMessage2(String message, JPanel component) {
        JLabel label = new JLabel(message);
        component.add(label, BorderLayout.SOUTH);
        component.add(label);
        label.setForeground(UiResources.validationFail);
        label.setVisible(true);
        component.revalidate();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException ignored) {}
            component.remove(label);
            for (Component c : component.getComponents()) {
                c.setVisible(true);
            }
            component.revalidate();
        }).start();
    }

}
