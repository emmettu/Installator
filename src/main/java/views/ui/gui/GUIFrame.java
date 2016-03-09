package views.ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
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
        frame.getContentPane().add(contentPanel.getJComponent());
        contentPanel.setLayout(new BorderLayout());
        GUIPanel header = new GUIPanel();
        header.setColor(Color.DARK_GRAY);
        header.setSize(WIDTH, HEIGHT / 10);
        contentPanel.addComponent(header, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public void addPanel(GUIComponent panel) {
        panels.add(panel);
        frame.pack();
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        GUIComponent currentPanel = panels.get(currentPanelIndex);
        contentPanel.addComponent(currentPanel, BorderLayout.CENTER);
        frame.pack();
        contentPanel.display();
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
