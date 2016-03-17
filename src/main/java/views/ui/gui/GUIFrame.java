package views.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 26/01/16.
 * Installer frame
 */
public class GUIFrame implements Frame {

    private List<GUIComponent> panels = new ArrayList<>();
    private int currentPanelIndex = 0;
    private GUIPanel contentPanel = new GUIPanel();
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int HEADER_HEIGHT = 50;
    private JFrame frame = new JFrame();

    public GUIFrame() {
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(contentPanel.getJComponent());
        contentPanel.setLayout(new BorderLayout());
        GUIPanel header = new GUIPanel();
        header.setColor(Color.DARK_GRAY);
        header.setSize(WIDTH, HEADER_HEIGHT);
        contentPanel.addComponent(header, BorderLayout.NORTH);
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
        GUIComponent currentPanel = panels.get(currentPanelIndex);
        contentPanel.addComponent(currentPanel, BorderLayout.CENTER);
        currentPanel.display();
        frame.pack();
    }

    @Override
    public void nextPanel() {
        contentPanel.remove(panels.get(currentPanelIndex));
        currentPanelIndex++;
        display();
    }

    @Override
    public void previousPanel() {
        contentPanel.remove(panels.get(currentPanelIndex));
        currentPanelIndex--;
        display();
    }

    @Override
    public void quit() {
        frame.dispose();
    }

}
