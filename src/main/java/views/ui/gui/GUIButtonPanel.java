package views.ui.gui;

/**
 * Created by eunderhi on 09/03/16.
 */
public class GUIButtonPanel extends GUIPanel {

    private GUIButton next = new GUIButton("next");
    private GUIButton prev = new GUIButton("prev");
    private GUIButton quit = new GUIButton("quit");

    public GUIButtonPanel() {
        GUIPanel panel = new GUIPanel();
        panel.setSize(500, 50);
        panel.addComponent(next);
        panel.addComponent(prev);
        panel.addComponent(quit);
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
