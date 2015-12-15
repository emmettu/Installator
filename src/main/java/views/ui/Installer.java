package views.ui;

import views.View;
import views.ui.panels.Panel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 15/12/15.
 */
public class Installer implements View {

    private List<Panel> panels = new ArrayList<>();
    private int currentPanelIndex = 0;

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        panels.get(currentPanelIndex);
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

    public void addPanel(Panel panel) {
        panels.add(panel);
    }

}
