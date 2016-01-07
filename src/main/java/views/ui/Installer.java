package views.ui;

import views.View;
import views.ui.panels.GUIPanel;
import views.ui.panels.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 15/12/15.
 */
public class Installer implements View {

    private List<GUIPanel> panels = new ArrayList<>();
    private int currentPanelIndex = 0;
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public Installer() {
        setUpFrame();
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        buttonPanel = new JPanel();
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        frame.pack();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void setUpFrame() {
        frame = new JFrame("Installer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        panels.get(currentPanelIndex).build(contentPanel);
        contentPanel.updateUI();
    }

    public Panel getCurrentPanel() {
        return panels.get(currentPanelIndex);
    }

    public void goToPanel(int i) {
        currentPanelIndex = i;
    }

    public void next() {
        currentPanelIndex++;
    }

    public void previous() {
        currentPanelIndex--;
    }

    public void addPanel(GUIPanel panel) {
        panel.setFrame(frame);
        panels.add(panel);
    }

    public void addNavButton(JButton button) {
        buttonPanel.add(button);
    }

}
