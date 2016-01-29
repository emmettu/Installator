package views.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 26/01/16.
 */
public class GUIFrame implements Frame {

    private List<GUIComponent> panels = new ArrayList<>();
    private int currentPanelIndex = 0;
    private GUIPanel contentPanel = new GUIPanel();
    private GUIPanel buttonPanel = new GUIPanel();
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private JFrame frame = new JFrame();

    public GUIFrame() {
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    public void addPanel(GUIComponent panel) {
        panels.add(panel);
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        frame.getContentPane().removeAll();
        GUIComponent currentPanel = panels.get(currentPanelIndex);
        frame.getContentPane().add(currentPanel.getJComponent());
    }

    @Override
    public void nextPanel() {
        currentPanelIndex++;
    }

    @Override
    public void previousPanel() {
        currentPanelIndex--;
    }

}
