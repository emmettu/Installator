package views.ui.gui;

import views.ui.box.GUIBox;

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

        GridBagConstraints gbcGlue = new GridBagConstraints();
        gbcGlue.weightx = 1.0;
        gbcGlue.weighty = 1.0;
        gbcGlue.fill = GridBagConstraints.BOTH;

        addComponent(GUIBox.createGlue(), gbcGlue);

        addComponent(GUIBox.createHorizontalGlue(),gbcGlue);

        //quitButton = ButtonFactory.createGrayGradientButton(langpack.getString("installer.quit"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_END;
        addComponent(quit, gbc);
        addComponent(GUIBox.createRigidArea(new Dimension(5, 0)));
        // prevButton.setFont(prevButton.getFont().deriveFont(Font.BOLD));
        addComponent(prev, gbc);

        addComponent(GUIBox.createRigidArea(new Dimension(5, 0)));
        addComponent(next,gbc);

        addComponent(GUIBox.createRigidArea(new Dimension(5, 0)));
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
