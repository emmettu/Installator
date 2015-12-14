package views.ui.panels;

import views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 11/12/15.
 */
public class ConsolePanel extends Panel {

    private List<View> components = new ArrayList<>();

    @Override
    protected void displayPanel() {
        for(View component : components) {
            component.update();
        }
    }

    public void addComponent(View component) {
        components.add(component);
    }

}
