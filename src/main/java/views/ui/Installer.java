package views.ui;

import views.View;
import views.ui.panels.GUIPanel;
import views.ui.panels.Panel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 15/12/15.
 */
public class Installer implements View {

    private List<GUIPanel> panels = new ArrayList<>();
    private int currentPanelIndex = 0;
    private JFrame frame;

    public Installer() {
            frame = new JFrame("Installer");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        panels.get(currentPanelIndex).display();
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
        panel.build();
        panels.add(panel);
    }

}
