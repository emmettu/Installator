package views.ui.gui;

import views.ui.gui.layout.Constraints;

import java.awt.*;

/**
 * Created by eunderhi on 27/05/16.
 */
public class GUILabeledComponent<T extends GUIComponent> extends GUIPanel {

    private T component;
    private GUILabel label;
    private int spacing;

    public GUILabeledComponent(String label, T component) {
        setLayout(new GridBagLayout());
        this.label = new GUILabel(label);
        this.component = component;
        build();
    }

    private void build() {
        addComponent(label, Constraints.createAlignedElementConstraint(1, 0, 0, GridBagConstraints.NONE));
        addComponent(component, Constraints.createAlignedElementConstraint(1, 1, 0, GridBagConstraints.HORIZONTAL));
    }

    public T getComponent() {
        return component;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

}
