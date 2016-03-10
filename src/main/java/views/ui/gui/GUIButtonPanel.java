package views.ui.gui;

/**
 * Created by eunderhi on 09/03/16.
 */
public class GUIButtonPanel extends GUIPanel {

    public GUIButtonPanel() {
        GUIPanel panel = new GUIPanel();
        panel.setSize(200, 200);
        panel.addComponent(new GUIButton("Next"));
        panel.addComponent(new GUIButton("Previous"));
        panel.addComponent(new GUIButton("Quit"));
        setJComponent(panel.getJComponent());
    }

}
