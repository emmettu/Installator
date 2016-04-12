package views.ui.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 09/03/16.
 */
public class GUIButtonPanel extends GUIPanel {

    private GUIButton quit = new GUIButton("quit");
    private GUIButton prev = new GUIButton("prev");
    private GUIButton next = new GUIButton("next");

    public GUIButtonPanel() {
        GUIPanel panel = new GUIPanel();
        panel.setSize(500, 50);
        setJComponent(panel.getJComponent());
        initializeLayout();
    }

    private void initializeLayout() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(3, 3, 3, 3);
        addComponent(quit, gbc);
        addComponent(prev, gbc);
        addComponent(next, gbc);
    }

    public GUIButton getNext() {
        return next;
    }

    public GUIButton getPrev() {
        return prev;
    }

    public GUIButton getQuit() {
        return quit;
    }

}
