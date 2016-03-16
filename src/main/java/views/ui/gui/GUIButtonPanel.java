package views.ui.gui;

/**
 * Created by eunderhi on 09/03/16.
 */
public class GUIButtonPanel extends GUIPanel {

    private GUIButton quit = new GUIButton("quit");
    private GUIButton prev = new GUIButton("prev");
    private GUIButton next = new GUIButton("next");

    public GUIButtonPanel() {
        GUIPanel panel = new GUIPanel();
        panel.setSize(500, 50);
        panel.addComponent(quit);
        panel.addComponent(prev);
        panel.addComponent(next);
        setJComponent(panel.getJComponent());
    }

    public GUIButton getNext() {
        return next;
    }

    public GUIButton getPrev() {
        return prev;
    }

    public GUIButton getQuit() {
        return quit;
    }

}
