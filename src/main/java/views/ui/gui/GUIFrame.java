package views.ui.gui;

import views.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 26/01/16.
 */
public class GUIFrame<T extends GUIComponent> extends Frame {

    private List<GUIComponent> panels = new ArrayList<>();
    private int currentPanelIndex = 0;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private JFrame frame;

    public GUIFrame() {
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {

    }

    void addPanel() {
    }

    void removePanel() {

    }

    @Override
    void addPanel(View panel) {
                
    }

    @Override
    void removePanel(View panel) {

    }

    @Override
    void nextPanel() {

    }

    @Override
    void previousPanel() {

    }

}
