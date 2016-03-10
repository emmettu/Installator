package views.ui.gui;

import java.awt.*;

/**
 * Created by eunderhi on 09/03/16.
 */
public abstract class GUIFramePanel extends GUIPanel {

    private String title;
    protected GUIPanel buttonPanel;
    protected GUIPanel contentPanel;

    public GUIFramePanel(String title) {
        this.title = title;
        setLayout(new BorderLayout());
        buttonPanel = new GUIPanel();
        contentPanel = new GUIPanel();
        contentPanel.setLayout(new GridBagLayout());
        build();
        buildButtonPanel();
        addComponent(buttonPanel, BorderLayout.SOUTH);
        addComponent(contentPanel, BorderLayout.CENTER);
    }

    protected abstract void build();
    protected abstract void buildButtonPanel();

}
