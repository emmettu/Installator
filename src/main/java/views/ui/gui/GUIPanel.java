package views.ui.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eunderhi on 29/01/16.
 */
public class GUIPanel extends GUIComponent {

    public GUIPanel() {
        setJComponent(new JPanel());
    }

    public GUIPanel(LayoutManager2 mngr) {
        setJComponent(new JPanel(mngr));
    }

    public void setLayout(LayoutManager2 layout) {
        getJComponent().setLayout(layout);
    }

    public void addComponent(GUIComponent component) {
        JComponent jComponent = component.getJComponent();
        getJComponent().add(jComponent);
    }

    public void addComponent(GUIComponent component, String pos) {
        JComponent jComponent = component.getJComponent();
        getJComponent().add(jComponent, pos);
    }

    public void addComponent(GUIComponent component, GridBagConstraints gbc) {
        getJComponent().add(component.getJComponent(), gbc);
    }

    public void setColor(Color col) {
        getJComponent().setBackground(col);
    }

    public void setSize(int width, int height) {
        getJComponent().setPreferredSize(new Dimension(width, height));
    }

}
